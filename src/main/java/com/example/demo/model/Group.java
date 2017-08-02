package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Created by 1 on 29.07.2017.
 */

@Entity
@Table(name = "STUDENT_GROUP")
@Data
@AllArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "BRANCH", nullable = false)
    private String branch;

    @Column(name = "MAX_QUANTITY_OF_STUDENTS", nullable = false)
    private Short maxQuantityOfStudents;

    @Column(name = "CURRENT_QUANTITY_OF_STUDENTS")
    private Short currentQuantityOfStudents;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Student> students;

    public void incrementCurrentQuantityOfStudents(){
        currentQuantityOfStudents++;
    }

    public void decrementCurrentQuantityOfStudents(){
        if(currentQuantityOfStudents > 0)
            currentQuantityOfStudents--;
    }

    public boolean canAddStudent(){
        return maxQuantityOfStudents > currentQuantityOfStudents;
    }

    public boolean overloaded(){
        return maxQuantityOfStudents < currentQuantityOfStudents;
    }
}
