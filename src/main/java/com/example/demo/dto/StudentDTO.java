package com.example.demo.dto;

import com.example.demo.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 1 on 25.07.2017.
 */
@Data
@AllArgsConstructor
public class StudentDTO {
    private Long id;
    private String name;
    private String surname;
    private Double averageGrade;
    private String birthDate;
    private Long group_id;

    public StudentDTO(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.surname = student.getSurname();
        this.averageGrade = student.getAverageGrade();
        this.birthDate = student.getBirthDate().toString();
        this.group_id = student.getGroup().getId();
    }

}
