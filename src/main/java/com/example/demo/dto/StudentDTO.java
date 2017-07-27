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
        this.grade = getAverage(student);
        this.date = student.getDate().toString();
    }

    private Double getAverage(Student student){
        Set<Subject> subjects = student.getSubjects();
        double sum = 0;

        if(subjects.isEmpty())  {
            return student.getGrade(); // consider returning current grade, `cause I'm confused when sending some grade and getting 0.0 if there're no subjects
        }

        for(Subject subject : subjects){
            sum += subject.getGrade();
        }

        return sum/subjects.size();
    }
}
