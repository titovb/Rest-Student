package com.example.demo.dto;

import com.example.demo.model.Group;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 1 on 29.07.2017.
 */
@Data
@AllArgsConstructor
public class GroupDTO {
    private Long id;
    private String name;
    private String branch;
    private short maxQuantityOfStudents;
    private short currentQuantityOfStudents = 0;

    public GroupDTO(Group group){
        this.id = group.getId();
        this.name = group.getName();
        this.branch = group.getBranch();
        this.currentQuantityOfStudents = group.getCurrentQuantityOfStudents();
        this.maxQuantityOfStudents = group.getMaxQuantityOfStudents();
    }
}
