package com.fullstackbd.tahsin.school.repository;

import com.fullstackbd.tahsin.school.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {
}
