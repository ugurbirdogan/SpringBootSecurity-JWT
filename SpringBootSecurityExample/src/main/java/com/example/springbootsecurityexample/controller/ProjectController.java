package com.example.springbootsecurityexample.controller;

import com.example.springbootsecurityexample.model.Project;
import com.example.springbootsecurityexample.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @PostMapping
    public void addProject(@RequestBody Project project){
        userDetailService.addProject(project);
    }

    @GetMapping
    public List<Project> getProjects(){
        return userDetailService.getProjects();
    }

    @PutMapping("/{id}")
    public void editProject(@PathVariable long id, @RequestBody Project project){

        userDetailService.editProject(id,project);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable long id){

        userDetailService.deleteProject(id);
    }



}
