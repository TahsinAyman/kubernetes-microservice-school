package com.fullstackbd.tahsin.school.service;

import com.fullstackbd.tahsin.school.entity.School;
import com.fullstackbd.tahsin.school.entity.SchoolStudents;

import java.net.ConnectException;
import java.util.List;

public interface SchoolService {
    List<School> findAll();
    List<SchoolStudents> findAllWithStudent() throws ConnectException;
    School findById(Long id);
    School save(School school);
    School save(Long id, School school);
    School deleteById(Long id);
}
