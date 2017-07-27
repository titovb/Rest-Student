package com.example.demo.controller;

import com.example.demo.dto.SubjectDTO;
import com.example.demo.service.SubjectService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j
@RestController
@RequestMapping(value = "/student/{studentId}")
public class SubjectController {

    @Autowired
    private SubjectService service;

    @RequestMapping(method = RequestMethod.GET, value = "/get_subjects")
    public List<SubjectDTO> getSubjectsByStudentID(@PathVariable(name = "studentId") Long studentId){
        log.info(">>>>>>>>>> " + studentId);
        return service.getSubjectsByStudentID(studentId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add_subject")
    public SubjectDTO addSubjectForStudent(@RequestBody SubjectDTO dto){
        return service.addSubjectForStudent(dto);
    }

}
