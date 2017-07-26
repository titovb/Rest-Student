package com.example.demo.service;

import com.example.demo.dao.StudentDAO;
import com.example.demo.dao.SubjectDAO;
import com.example.demo.dto.SubjectDTO;
import com.example.demo.model.Student;
import com.example.demo.model.Subject;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by 1 on 26.07.2017.
 */
@Log4j
@Service
@Transactional
public class SubjectService {

    @Autowired
    private SubjectDAO dao;

    @Autowired
    private StudentDAO studentDAO;

    public List<SubjectDTO> getSubjectsByStudentID(Long studentId) {
        return convertSubjectsToSubjectDTOs(dao.findByStudent(studentId));
    }

    public SubjectDTO addSubjectForStudent(SubjectDTO dto){
        Subject saved = dao.save(convertSubjectDTOToSubject(dto));
        //studentDAO.findOne(dto.getStudentID()).getSubjects().add(saved);
        return convertSubjectToSubjectDTO(saved);
    }

    private SubjectDTO convertSubjectToSubjectDTO(Subject subject){
        return new SubjectDTO(subject);
    }

    private Subject convertSubjectDTOToSubject(SubjectDTO dto){
        Subject subject = new Subject();
        subject.setId(dto.getId());
        subject.setName(dto.getName());
        subject.setGrade(dto.getGrade());
        subject.setStudent(studentDAO.findOne(dto.getStudentID()));
        return subject;
    }

    private List<SubjectDTO> convertSubjectsToSubjectDTOs(List<Subject> subjects){
        List<SubjectDTO> dtos = new ArrayList<>();
        for(int i=0;i<subjects.size();i++){
            dtos.add(convertSubjectToSubjectDTO(subjects.get(i)));
        }
        return dtos;
    }
}
