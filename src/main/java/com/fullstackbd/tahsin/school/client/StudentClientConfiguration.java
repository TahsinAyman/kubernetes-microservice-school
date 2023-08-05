package com.fullstackbd.tahsin.school.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class StudentClientConfiguration {
    @Bean
    public WebClient studentWebClient() {
        return WebClient
                .builder()
                .baseUrl("http://student-service:8080")
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
