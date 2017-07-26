package com.example.demo.dao;

import com.example.demo.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by 1 on 26.07.2017.
 */
public interface SubjectDAO extends JpaRepository<Subject, Long> {
    List<Subject> findByStudent(Long studentId);
}
