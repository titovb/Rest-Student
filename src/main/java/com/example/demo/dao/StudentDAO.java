package com.example.demo.dao;

import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by 1 on 25.07.2017.
 */
public interface StudentDAO extends JpaRepository<Student, Long>{
    List<Student> findBySurname(String surname);

    @Query("select s.name from Student s")
    List<String> getNames();

    @Query("select s from Student s where s.grade >= 4")
    List<Student> getBestStudents();

}
