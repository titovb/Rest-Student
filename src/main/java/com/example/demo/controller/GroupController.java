package com.example.demo.controller;

import com.example.demo.dto.GroupDTO;
import com.example.demo.dto.StudentDTO;
import com.example.demo.service.GroupService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 1 on 29.07.2017.
 */
@Log4j
@RestController
@RequestMapping(value = "/group")
public class GroupController {

    @Autowired
    private GroupService service;

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public GroupDTO addGroup(@RequestBody GroupDTO dto){
        log.info("GroupController - POST request: to add new group " + dto);
        return service.addGroup(dto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/all")
    public List<GroupDTO> getAllGroups(){
        log.info("GroupController - GET request: to get all groups");
        return service.getAllGroups();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/{id}")
    public GroupDTO getGroupByGroupId(@PathVariable(name = "id") Long id){
        log.info("GroupController - GET request: to get group with id=[" + id + "]");
        return service.getGroupById(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public void deleteGroupById(@PathVariable(name = "id") Long id){
        log.info("GroupController - DELETE request: to delete group with id=[" + id + "]");
        service.deleteGroupById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public GroupDTO updateGroup(@RequestBody GroupDTO dto){
        log.info("GroupController - PUT request: to update group " + dto);
        return service.updateGroup(dto);
    }
}
