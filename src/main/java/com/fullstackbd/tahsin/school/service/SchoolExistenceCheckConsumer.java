package com.fullstackbd.tahsin.school.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstackbd.tahsin.common.events.SchoolExistenceCheckEvent;
import com.fullstackbd.tahsin.common.events.StudentSaveFailedEvent;
import com.fullstackbd.tahsin.common.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SchoolExistenceCheckConsumer implements ConsumerService {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SchoolService service;
    @Autowired
    private StudentSaveFailedProducer studentSaveFailedProducer;

    @Override
    @KafkaListener(topics = "school-existence-check-event")
    public void consume(String event) {
        try {
            SchoolExistenceCheckEvent schoolExistenceCheckEvent = objectMapper.readValue(event, SchoolExistenceCheckEvent.class);
            if (service.findById(schoolExistenceCheckEvent.getSchoolId()) == null) {
                studentSaveFailedProducer.produce(
                        StudentSaveFailedEvent
                                .builder()
                                .id(schoolExistenceCheckEvent.getStudentId())
                                .build()
                );
            }
        } catch (Exception e) {
            throw new RuntimeException("JSON DECODE ERROR");
        }
    }

}
