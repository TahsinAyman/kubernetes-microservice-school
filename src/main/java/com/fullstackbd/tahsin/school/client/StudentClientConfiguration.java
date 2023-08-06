package com.fullstackbd.tahsin.school.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class StudentClientConfiguration {
    @Value("${microservice.student}")
    private String STUDENT_URL = null;
    @Bean
    public WebClient studentWebClient() {
        System.out.println(STUDENT_URL);
        return WebClient
                .builder()
                .baseUrl(STUDENT_URL)
                .build();
    }
    @Bean
    public StudentClient schoolClient() {
        HttpServiceProxyFactory httpServiceProxyFactory =
                HttpServiceProxyFactory
                        .builder(WebClientAdapter.forClient(this.studentWebClient()))
                        .build();
        return httpServiceProxyFactory.createClient(StudentClient.class);
    }
}
