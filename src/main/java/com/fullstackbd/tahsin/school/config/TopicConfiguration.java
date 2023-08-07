package com.fullstackbd.tahsin.school.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfiguration {

    @Bean
    public NewTopic schoolExistenceCheckEvent() {
        return TopicBuilder
                .name("school-existence-check-event")
                .build();
    }

}
