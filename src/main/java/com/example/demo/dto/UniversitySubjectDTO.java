package com.example.demo.dto;

import com.example.demo.model.UniversitySubject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 1 on 30.07.2017.
 */
@Data
@AllArgsConstructor
public class UniversitySubjectDTO {
    private Long id;
    private String name;

    public UniversitySubjectDTO(UniversitySubject universitySubject){
        this.id = universitySubject.getId();
        this.name = universitySubject.getName();
    }
}
