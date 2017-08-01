package com.example.demo.controller;

import com.example.demo.dto.GradeDTO;
import com.example.demo.service.GradeService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 1 on 30.07.2017.
 */
@Log4j
@RestController
@RequestMapping(value = "/grade")
public class GradeController {

    @Autowired
    private GradeService service;

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public GradeDTO addGrade(@RequestBody GradeDTO dto){
        log.info("GradeController - POST request: to add new grade " + dto);
        return service.addGrade(dto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "get/from_student/{student_id}")
    public List<GradeDTO> getGradesByStudentId(@PathVariable(name = "student_id") Long studentId){
        log.info("GradeController - GET request: to get grades by student_id=[" + studentId + "]");
        return service.getGradesByStudentId(studentId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public GradeDTO updateGrade(@RequestBody GradeDTO dto){
        log.info("GradeController - PUT request: to update grade " + dto);
        return service.updateGrade(dto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public void deleteGrade(@PathVariable Long id){
        log.info("Grade Controller - DELETE request: to delete grade with id=[" + id + "]");
        service.deleteGrade(id);
    }
}
