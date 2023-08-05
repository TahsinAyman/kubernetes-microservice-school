package com.fullstackbd.tahsin.school.controller;

import com.fullstackbd.tahsin.school.entity.Message;
import com.fullstackbd.tahsin.school.entity.School;
import com.fullstackbd.tahsin.school.entity.SchoolStudents;
import com.fullstackbd.tahsin.school.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.ConnectException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    private SchoolService service;

    @GetMapping
    public List<School> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        School school = service.findById(id);
        if (school != null) {
            return ResponseEntity.status(HttpStatus.OK).body(school);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Message
                        .builder()
                        .message("School not found")
                        .result(false)
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .build()
        );
    }

    @GetMapping("/students")
    public ResponseEntity<?> findAllWithStudent() {
        List<SchoolStudents> list;
        try {
            list = service.findAllWithStudent();
        } catch (ConnectException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(
                Message
                    .builder()
                    .message("Service Unavailable")
                    .result(false)
                    .statusCode(HttpStatus.SERVICE_UNAVAILABLE.value())
                    .build()
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(list);

    }

    @PostMapping
    public School save(@RequestBody School school) {
        return service.save(school);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody School school) {
        School updatedSchool = service.save(id, school);
        if (updatedSchool != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedSchool);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Message
                        .builder()
                        .message("School not found")
                        .result(false)
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        School deletedSchool = service.deleteById(id);
        if (deletedSchool != null) {
            return ResponseEntity.status(HttpStatus.OK).body(deletedSchool);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Message
                        .builder()
                        .message("School not found")
                        .result(false)
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .build()
        );
    }
}
