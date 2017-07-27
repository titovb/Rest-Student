package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 1 on 25.07.2017.
 */
@Entity
@Table(name = "STUDENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "SURNAME", nullable = false)
    private String surname;

    @Column(name = "GRADE")
    private Double grade;

    @Column(name = "BIRTH_DATE", nullable = false)
    private LocalDate date;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Subject> subjects = new HashSet<>();

    public void setSubjects(Set<Subject> subjects){
        if(subjects!=null){
            this.subjects = subjects;
        }
    }
}
