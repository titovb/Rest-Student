package com.example.demo.service;

import com.example.demo.dao.StudentDAO;
import com.example.demo.dto.StudentDTO;
import com.example.demo.model.Student;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 1 on 25.07.2017.
 */
@Log4j
@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentDAO dao;

    public List<StudentDTO> getStudents() {
        return dao.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<StudentDTO> addStudents(List<StudentDTO> dtos) {
        List<StudentDTO> savedList = new ArrayList<>();
        for (StudentDTO dto : dtos) {
            savedList.add(addStudent(dto));
        }
        return savedList;
    }

    public StudentDTO addStudent(StudentDTO dto) {
        Student saved = dao.save(convertToEntity(dto));
        return convertToDTO(saved);
    }

    public StudentDTO updateStudent(StudentDTO dto) {
        Student saved = dao.save(convertToEntity(dto));
        return convertToDTO(saved);
    }

    public void deleteStudent(Long id) {
        dao.delete(id);
    }

    public StudentDTO getStudent(Long id) {
        Student founded = dao.findOne(id);
        return convertToDTO(founded);
    }

    public List<String> getNames() {
        return dao.getNames();
    }

    public List<StudentDTO> findBySurname(String surname) {
        List<StudentDTO> studentDTOs = dao.findBySurname(surname).stream().map(this::convertToDTO).collect(Collectors.toList());
        log.info("Founded [" + studentDTOs.size() + "] students with [" + surname + "] surname");
        return studentDTOs;
    }

    public List<StudentDTO> getBestStudents() {
        return convertAllToDTO(dao.getBestStudents());
    }

    private StudentDTO convertToDTO(Student student) {
        return new StudentDTO(student);
    }

    private Student convertToEntity(StudentDTO dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setSurname(dto.getSurname());
        log.info(dto.getGrade());
        student.setGrade(dto.getGrade());
        student.setDate(LocalDate.parse(dto.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))); // You may try this solution. From next Monday I can help u to use LocalDate from Java 8
        return student;
    }

    private java.util.Date dateParse(String date) {
        java.util.Date result = null;
        try {
            result = new SimpleDateFormat("dd.MM.yyyy").parse(date);
        } catch (ParseException ex) {
            log.info("Exception in StudentService in method dateParse:\n " + ex.getMessage());
        }
        return result;
    }

    private List<StudentDTO> convertAllToDTO(List<Student> students) {
        List<StudentDTO> dtos = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            dtos.add(convertToDTO(students.get(i)));
        }
        return dtos;

        /** try to use this way :) */
        // return students.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
