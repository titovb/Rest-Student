package com.example.demo.service;

import com.example.demo.dao.GradeDAO;
import com.example.demo.dao.StudentDAO;
import com.example.demo.dao.UniversitySubjectDAO;
import com.example.demo.dto.GradeDTO;
import com.example.demo.model.Grade;
import com.example.demo.model.Student;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 1 on 30.07.2017.
 */
@Log4j
@Service
@Transactional
public class GradeService {

    @Autowired
    private GradeDAO dao;

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private UniversitySubjectDAO subjectDAO;

    public GradeDTO addGrade(GradeDTO dto) {
        Grade saved = dao.save(convertDTOToEntity(dto));
        studentDAO.findOne(saved.getStudent().getId()).calculateAverageGrade();
        log.info("GradeService - addGrade: added grade " + convertEntityToDTO(saved)); // why u converting two times?
        return convertEntityToDTO(saved);
    }

    public List<GradeDTO> getGradesByStudentId(Long studentId) {
        List<GradeDTO> result = studentDAO.findOne(studentId).getGrades().stream().map(this::convertEntityToDTO).collect(Collectors.toList());
        log.info("GradeService - getGradesByStudentId: got [" + result.size() + "] grades by student_id=[" + studentId + "]");
        return result;
    }

    public GradeDTO updateGrade(GradeDTO dto) {
        Grade saved = dao.save(convertDTOToEntity(dto));
        studentDAO.findOne(saved.getStudent().getId()).calculateAverageGrade(); // here u calculating average grade for student after Grade was changed? It so why u don't save a student?
        log.info("GradeService - updateGrade: updated grade " + saved);
        return convertEntityToDTO(saved);
    }

    public void deleteGrade(Long id) {
        Student student = dao.findOne(id).getStudent();
        dao.delete(id);
        if(student!=null)
            student.calculateAverageGrade();
        log.info("GradeService - deleteGrade: deleted grade with id=[" + id + "]");
    }

    private GradeDTO convertEntityToDTO(Grade grade){
        return new GradeDTO(grade);
    }

    private Grade convertDTOToEntity(GradeDTO dto){
        Grade grade = new Grade();
        grade.setId(dto.getId());
        grade.setDate(LocalDate.parse(dto.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        grade.setValue(dto.getValue());
        grade.setStudent(studentDAO.findOne(dto.getStudentId()));
        grade.setSubject(subjectDAO.findByName(dto.getSubjectName()).get(0));
        return grade;
    }
}
