package com.example.demo.dao;

import com.example.demo.model.UniversitySubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 1 on 30.07.2017.
 */
@Repository
public interface UniversitySubjectDAO extends JpaRepository<UniversitySubject, Long>{

    @Query("select s from UniversitySubject s where upper(s.name) like upper(:name)")
    List<UniversitySubject> findByName(@Param(value = "name") String name);
}
