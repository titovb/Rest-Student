package com.example.demo.dto;

import com.example.demo.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 1 on 26.07.2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO {
    private Long id;
    private String name;
    private Double grade;
    private Long studentID;

    public SubjectDTO(Subject subject) {
        this.id = subject.getId();
        this.name = subject.getName();
        this.grade = subject.getGrade();
        this.studentID = subject.getStudent().getId();
    }
}
