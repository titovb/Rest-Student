package com.example.demo.controller;

import com.example.demo.dto.StudentDTO;
import com.example.demo.model.Group;
import com.example.demo.service.StudentService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 1 on 25.07.2017.
 */
@Log4j
@RestController
@RequestMapping(value = "student")
public class StudentController {

    @Autowired
    private StudentService service;

    @RequestMapping(method = RequestMethod.GET, value = "/get/all")
    public List<StudentDTO> getAllStudents(){
        log.info("StudentController - GET request: to get all students");
        return service.getAllStudents();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/from_group/{group_id}")
    public List<StudentDTO> getStudentsByGroupId(@PathVariable(name = "group_id") Long groupId){
        log.info("StudentController - GET request: to get all students from group by group_id=[" + groupId + "]");
        return service.getStudentsByGroupId(groupId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/{id}")
    public StudentDTO getStudentById(@PathVariable Long id){
        log.info("StudentController - GET request: to get one student by id: [" + id + "]");
        return service.getStudentById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public StudentDTO addStudent(@RequestBody StudentDTO dto){
        log.info("StudentController - POST request: to add new student: " + dto);
        return service.addStudent(dto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add/all")
    public List<StudentDTO> addStudents(@RequestBody List<StudentDTO> dtos){
        log.info("StudentController - POST request: to add many new students");
        return service.addStudents(dtos);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public void deleteStudent(@PathVariable Long id){
        log.info("StudentController - DELETE request: to delete student with id=[" + id + "]");
        service.deleteStudent(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public StudentDTO updateStudent(@RequestBody StudentDTO dto){
        log.info("StudentController - PUT request: to update student " + dto);
        return service.updateStudent(dto);
    }
}
