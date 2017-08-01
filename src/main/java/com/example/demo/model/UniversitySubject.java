package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Created by 1 on 30.07.2017.
 */
@Entity
@Table(name = "UNIVERSITY_SUBJECT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UniversitySubject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<Grade> grades;
}
