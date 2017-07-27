package com.example.demo.dao;

import com.example.demo.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectDAO extends JpaRepository<Subject, Long> {

    @Query("select s from Subject s where s.student.id=:stId")
    List<Subject> findByStudent(@Param("stId") Long studentId);
}
