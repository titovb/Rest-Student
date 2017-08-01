package com.example.demo.dao;

import com.example.demo.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 1 on 30.07.2017.
 */
@Repository
public interface GradeDAO extends JpaRepository<Grade, Long>{

}
