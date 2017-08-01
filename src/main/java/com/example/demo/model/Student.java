package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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

    @Column(name = "AVERAGE_GRADE")
    private Double averageGrade = 0.0;

    @Column(name = "BIRTH_DATE", nullable = false)
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID", nullable = false)
    private Group group;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Grade> grades;

    public void calculateAverageGrade(){
        double sum = 0.0;
        for(Grade grade : grades){
            sum+=grade.getValue();
        }
        averageGrade = sum/grades.size();
    }
}
