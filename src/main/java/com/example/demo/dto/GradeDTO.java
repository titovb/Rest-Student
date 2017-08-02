package com.example.demo.dto;

import com.example.demo.model.Grade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 1 on 30.07.2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeDTO {
    private Long id;
    private String date;
    private Double value;
    private String subjectName;
    private Long studentId;

    public GradeDTO(Grade grade){
        this.id = grade.getId();
        this.date = grade.getDate().toString();
        this.value = grade.getValue();
        this.subjectName = grade.getSubject().getName();
        this.studentId = grade.getStudent().getId();
    }
}
