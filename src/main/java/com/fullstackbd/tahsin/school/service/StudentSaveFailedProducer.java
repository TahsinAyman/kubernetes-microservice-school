package com.fullstackbd.tahsin.school.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstackbd.tahsin.common.events.StudentSaveFailedEvent;
import com.fullstackbd.tahsin.common.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class StudentSaveFailedProducer implements ProducerService {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public <T> void produce(T event) {
        try {
            String body = objectMapper.writeValueAsString(event);
            kafkaTemplate.send("student-save-failed-event", body);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON DECODE ERROR");
        }
    }
}
