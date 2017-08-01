package com.example.demo.dao;

import com.example.demo.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 1 on 29.07.2017.
 */
@Repository
public interface GroupDAO extends JpaRepository<Group, Long> {
}
