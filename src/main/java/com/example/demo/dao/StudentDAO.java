package com.example.demo.dao;

import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 1 on 25.07.2017.
 */
@Repository
public interface StudentDAO extends JpaRepository<Student, Long>{
    @Query("select s from Student s where upper(s.surname) like upper(:surname)")
    List<Student> findBySurnameIgnoringCase(@Param("surname") String surname);

    @Query("select s.name from Student s")
    List<String> getNames();

    /*@Query("select s from Student s where s.grade >= 4")
    List<Student> getBestStudents();*/
}
