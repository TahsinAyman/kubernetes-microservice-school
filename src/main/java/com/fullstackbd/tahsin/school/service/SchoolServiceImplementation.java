package com.fullstackbd.tahsin.school.service;

import com.fullstackbd.tahsin.school.client.StudentClient;
import com.fullstackbd.tahsin.school.entity.School;
import com.fullstackbd.tahsin.school.entity.SchoolStudents;
import com.fullstackbd.tahsin.school.entity.Student;
import com.fullstackbd.tahsin.school.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SchoolServiceImplementation implements SchoolService {

    @Autowired
    private SchoolRepository repository;
    @Autowired
    private StudentClient client;

    @Override
    public List<School> findAll() {
        return repository.findAll();
    }

    @Override
    public List<SchoolStudents> findAllWithStudent() throws ConnectException {
        List<SchoolStudents> list = new ArrayList<>();
        for (School school : repository.findAll()) {
            try {
                List<Student> students = client.findById(school.getId());
                SchoolStudents schoolStudents = SchoolStudents
                        .builder()
                        .students(students)
                        .id(school.getId())
                        .name(school.getName())
                        .build();
                list.add(schoolStudents);
            } catch (Exception e) {
                throw new ConnectException("Service Unavailable");
            }
        }
        return list;
    }

    @Override
    public School findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public School save(School school) {
        return repository.save(school);
    }

    @Override
    public School save(Long id, School school) {
        School existingSchool = this.findById(id);
        if (existingSchool != null) {
            existingSchool.setName(school.getName());
            return repository.save(existingSchool);
        }
        return null;
    }

    public Integer add(Integer a, Integer b) {
        return a + b;
    }


    @Override
    public School deleteById(Long id) {
        School existingSchool = this.findById(id);
        if (existingSchool != null) {
            repository.deleteById(id);
            return existingSchool;
        }
        return null;
    }
}
