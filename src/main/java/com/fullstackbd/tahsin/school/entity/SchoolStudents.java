package com.fullstackbd.tahsin.school.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchoolStudents {
    private Long id;
    private String name;
    private List<Student> students;
}
