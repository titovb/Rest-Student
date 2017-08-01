package com.example.demo.service;

import com.example.demo.dao.GroupDAO;
import com.example.demo.dao.StudentDAO;
import com.example.demo.dto.GroupDTO;
import com.example.demo.dto.StudentDTO;
import com.example.demo.model.Group;
import com.example.demo.model.Student;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 1 on 29.07.2017.
 */
@Log4j
@Service
@Transactional
public class GroupService {

    @Autowired
    private GroupDAO dao;

    @Autowired
    private StudentService studentService;

    public GroupDTO addGroup(GroupDTO dto) {
        GroupDTO saved = convertEntityToDTO(dao.save(convertDTOtoEntity(dto)));
        log.info("GroupService - addGroup: added new group " + saved);
        return saved;
    }

    public List<GroupDTO> getAllGroups() {
        List<GroupDTO> result = dao.findAll().stream().map(this::convertEntityToDTO).collect(Collectors.toList());
        log.info("GroupService - getAll: got [" + result.size() + "] groups");
        return result;
    }

    public GroupDTO getGroupById(Long id) {
        GroupDTO result = convertEntityToDTO(dao.findOne(id));
        log.info("GroupService - getByID: got group with id=[" + id + "]");
        return result;
    }

    public void deleteGroupById(Long id) {
        List<Student> studentsFromGroup = dao.findOne(id).getStudents();
        for(int i=0;i<studentsFromGroup.size();i++){
            studentService.deleteStudent(studentsFromGroup.get(i).getId());
        }
        dao.delete(id);
        log.info("GroupService - deleteGroupById: deleted group with id=[" + id + "]");
    }

    public GroupDTO updateGroup(GroupDTO dto) {
        Group saved = dao.save(convertDTOtoEntity(dto));
        List<Student> studentsFromGroup = dao.findOne(dto.getId()).getStudents();
        int i = studentsFromGroup.size();
        while(saved.overloaded()){
            studentService.deleteStudent(studentsFromGroup.get(i).getId());
            saved.decrementCurrentQuantityOfStudents();
        }
        log.info("GroupService - updateGroup: updated group with id=[" + saved.getId() + "]");
        return convertEntityToDTO(saved);
    }

    private Group convertDTOtoEntity(GroupDTO dto){
        Group group = new Group();
        group.setId(dto.getId());
        group.setName(dto.getName());
        group.setBranch(dto.getBranch());
        group.setMaxQuantityOfStudents(dto.getMaxQuantityOfStudents());
        group.setCurrentQuantityOfStudents(dto.getCurrentQuantityOfStudents());
        return group;
    }

    private GroupDTO convertEntityToDTO(Group group){
        return new GroupDTO(group);
    }
}
