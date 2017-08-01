package com.example.demo.controller;

import com.example.demo.dto.UniversitySubjectDTO;
import com.example.demo.service.UniversitySubjectService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 1 on 30.07.2017.
 */
@Log4j
@RestController
@RequestMapping(value = "/subject")
public class UniversitySubjectController {

    @Autowired
    private UniversitySubjectService service;

    @RequestMapping(method = RequestMethod.GET, value = "/get/all")
    public List<UniversitySubjectDTO> getAllSubjects(){
        log.info("UniversitySubjectController - GET request: to get all subjects");
        return service.getAllSubjects();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/{id}")
    public UniversitySubjectDTO getSubjectById(@PathVariable Long id){
        log.info("UniversitySubjectController - GET request: to get subject with id=[" + id + "]");
        return service.getSubjectById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public UniversitySubjectDTO addSubject(@RequestBody UniversitySubjectDTO dto){
        log.info("UniversitySubjectController - POST request: to add new subject " + dto);
        return service.addSubject(dto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public void deleteSubject(@PathVariable Long id){
        log.info("UniversitySubjectController - DELETE request: to delete subject with id=[" + id + "]");
        service.deleteSubject(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public UniversitySubjectDTO updateSubject(@RequestBody UniversitySubjectDTO dto){
        log.info("UniversitySubjectController - PUT request: to update subject " + dto);
        return service.updateSubject(dto);
    }

}
