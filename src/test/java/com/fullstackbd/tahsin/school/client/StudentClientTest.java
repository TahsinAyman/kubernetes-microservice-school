package com.fullstackbd.tahsin.school.client;

import com.fullstackbd.tahsin.school.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentClientTest {
    @Autowired
    private StudentClient client;

    @Test
    public void testFindById() {
        List<Student> students = client.findById(1L);
        System.out.println(students);
    }

}