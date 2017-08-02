package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by 1 on 30.07.2017.
 */
@Entity
@Table(name = "GRADE")
@Data
@AllArgsConstructor
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "DATE", nullable = false)
    private LocalDate date;

    @Column(name = "VALUE", nullable = false)
    private Double value;

    @ManyToOne
    @JoinColumn(name = "STUDENT_ID", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "SUBJECT_ID", nullable = false)
    private UniversitySubject subject;
}
