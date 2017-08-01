package com.example.demo.service;

import com.example.demo.dao.GroupDAO;
import com.example.demo.dao.StudentDAO;
import com.example.demo.dto.StudentDTO;
import com.example.demo.model.Grade;
import com.example.demo.model.Group;
import com.example.demo.model.Student;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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

    @Autowired
    private GroupDAO groupDAO;

    @Autowired
    private GradeService gradeService;

    public List<StudentDTO> getAllStudents() {
        List<StudentDTO> result = dao.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
        log.info("StudentService - getStudents: got [" + result.size() + "] students");
        return result;
    }

    public StudentDTO getStudentById(Long id) {
        Student founded = dao.findOne(id);
        log.info("StudentService - getStudentById: got student with id=[" + id +"]");
        return convertToDTO(founded);
    }

    public List<StudentDTO> getStudentsByGroupId(Long groupId) {
        List<StudentDTO> result = convertAllToDTO(groupDAO.findOne(groupId).getStudents());
        log.info("StudentService - getStudentsByGroupId: got [" + result.size() + "] students from group with id=[" + groupId + "]");
        return result;
    }

    public List<StudentDTO> addStudents(List<StudentDTO> dtos) {
        List<StudentDTO> savedList = new ArrayList<>();
        for (StudentDTO dto : dtos) {
            savedList.add(addStudent(dto));
        }
        log.info("StudentService - addStudents: added [" + savedList.size() + "] new students");
        return savedList;
    }

    public StudentDTO addStudent(StudentDTO dto) {
        Group group = groupDAO.findOne(dto.getGroup_id());
        Student saved = null;
        if(group.canAddStudent()) {
            saved = dao.save(convertToEntity(dto));
            group.incrementCurrentQuantityOfStudents();
            log.info("StudentService - addStudent: added new student " + convertToDTO(saved));
            return convertToDTO(saved);
        }
        else{
            log.info("StudentService - addStudent: can't add student " + dto + "to group with id=[" + group.getId() + "] - overload");
            return null;
        }
    }

    public StudentDTO updateStudent(StudentDTO dto) {
        Student saved = dao.save(convertToEntity(dto));
        log.info("StudentService - updateStudent: updated student with id = [" + saved.getId() + "]");
        return convertToDTO(saved);
    }

    public void deleteStudent(Long id) {
        Student student = dao.findOne(id);
        Group group = groupDAO.findOne(student.getGroup().getId());
        List<Grade> grades = student.getGrades();
        for(int i=0;i<grades.size();i++){
            gradeService.deleteGrade(grades.get(i).getId());
        }
        dao.delete(id);
        if(group!=null) {
            group.decrementCurrentQuantityOfStudents();
        }
        log.info("StudentService - deleteStudent: deleted student with id=[" + id + "]");
    }

    private StudentDTO convertToDTO(Student student) {
        return new StudentDTO(student);
    }

    private Student convertToEntity(StudentDTO dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setSurname(dto.getSurname());
        student.setAverageGrade(dto.getAverageGrade());
        student.setBirthDate(LocalDate.parse(dto.getBirthDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))); // You may try this solution. From next Monday I can help u to use LocalDate from Java 8
        student.setGroup(groupDAO.findOne(dto.getGroup_id()));
        return student;
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
