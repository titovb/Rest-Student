package com.example.demo.controller;

import com.example.demo.dto.StudentDTO;
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
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private StudentService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<StudentDTO> getStudents(){
        log.info("STUDENT - GET request: to get all students");
        return service.getStudents();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public StudentDTO addStudent(@RequestBody StudentDTO dto){
        log.info("STUDENT - POST request: to add new student: " + dto);
        return service.addStudent(dto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add/all")
    public List<StudentDTO> addStudents(@RequestBody List<StudentDTO> dtos){
        log.info("STUDENT - POST request: to add many new students");
        return service.addStudents(dtos);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public void deleteStudent(@PathVariable Long id){
        log.info("STUDENT - DELETE request: to delete student with id=[" + id + "]");
        service.deleteStudent(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public StudentDTO updateStudent(@RequestBody StudentDTO dto){
        log.info("STUDENT - PUT request: to update student"); // I recommend to an ID to log
        return service.updateStudent(dto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/{id}")
    public StudentDTO getStudent(@PathVariable Long id){
        log.info("STUDENT - GET request: to get one student"); // I recommend to an ID to log
        return service.getStudent(id); // I recommend to name the method as getStudentById
    }

    @RequestMapping(method = RequestMethod.GET, value = "/names")
    public List<String> getNames(){
        log.info("STUDENT - GET request: to get names of students");
        return service.getNames();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getBySurname/{surname}")
    public List<StudentDTO> findBySurname(@PathVariable String surname){
        log.info("STUDENT - GET request: to find students by surname");
        return service.findBySurname(surname); // it searches without ignoring word case (Smith, but not smith). Bug or feature? :)
    }


    // what means "best"? According to "Clean Code" a better name may be the "getStudentsWithAverageOver4" or add a comment with explaining otherwise
    @RequestMapping(method = RequestMethod.GET, value = "/get/best")
    public List<StudentDTO> getBestStudents(){
        log.info("STUDENT - GET request: to get best students");
        return service.getBestStudents();
    }
}
