package com.example.demo.service;

import com.example.demo.dao.UniversitySubjectDAO;
import com.example.demo.dto.UniversitySubjectDTO;
import com.example.demo.model.Grade;
import com.example.demo.model.UniversitySubject;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 1 on 30.07.2017.
 */
@Log4j
@Service
@Transactional
public class UniversitySubjectService {

    @Autowired
    private UniversitySubjectDAO dao;

    @Autowired
    private GradeService gradeService;

    public List<UniversitySubjectDTO> getAllSubjects() {
        List<UniversitySubjectDTO> result = dao.findAll().stream().map(this::convertEntityToDTO).collect(Collectors.toList());
        log.info("UniversitySubjectService - getAllSubjects: got [" + result.size() + "] subjects");
        return result;
    }

    public UniversitySubjectDTO getSubjectById(Long id) {
        UniversitySubjectDTO result = convertEntityToDTO(dao.findOne(id));
        log.info("UniversitySubjectService - getSubjectById: got subject with id=[" + id + "]");
        return result;
    }

    public UniversitySubjectDTO addSubject(UniversitySubjectDTO dto) {
        UniversitySubjectDTO result = convertEntityToDTO(dao.save(convertDTOToEntity(dto)));
        log.info("UniversitySubjectService - addSubject: added subject " + result);
        return result;
    }

    public void deleteSubject(Long id) {
        UniversitySubject subject = dao.findOne(id);
        List<Grade> grades = subject.getGrades();
        for(int i=0;i<grades.size();i++){
            gradeService.deleteGrade(grades.get(i).getId());
        }
        dao.delete(id);
        log.info("UniversitySubjectService - deleteSubject: deleted subject with id=[" + id + "]");
    }

    public UniversitySubjectDTO updateSubject(UniversitySubjectDTO dto) {
        UniversitySubject saved = dao.save(convertDTOToEntity(dto));
        log.info("UniversitySubjectService - updateSubject: updated subject with id=[" + dto.getId() + "]");
        return convertEntityToDTO(saved);
    }

    private UniversitySubjectDTO convertEntityToDTO(UniversitySubject universitySubject){
        return new UniversitySubjectDTO(universitySubject);
    }

    private UniversitySubject convertDTOToEntity(UniversitySubjectDTO dto){
        UniversitySubject universitySubject = new UniversitySubject();
        universitySubject.setId(dto.getId());
        universitySubject.setName(dto.getName());
        return universitySubject;
    }
}
