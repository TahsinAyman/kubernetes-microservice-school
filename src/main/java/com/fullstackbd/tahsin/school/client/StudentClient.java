package com.fullstackbd.tahsin.school.client;

import com.fullstackbd.tahsin.school.entity.Student;

import jakarta.persistence.Entity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import java.util.List;

@HttpExchange("/student")
public interface StudentClient {
    @GetExchange("/school/{id}")
    List<Student> findById(@PathVariable("id") Long id);
}
