package com.example.demo.dto;

import com.example.demo.model.Student;
import com.example.demo.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Created by 1 on 25.07.2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private Long id;
    private String name;
    private String surname;
    private Double grade = 0.0;
    private String date;

    public StudentDTO(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.surname = student.getSurname();
        this.grade = getAverage(student.getSubjects());
        this.date = student.getDate().toString();
    }

    private Double getAverage(Set<Subject> subjects){
        double sum = 0;

        if(subjects.isEmpty())
            return 0.0;

        for(Subject subject : subjects){
            sum += subject.getGrade();
        }

        return sum/subjects.size();
    }
}
