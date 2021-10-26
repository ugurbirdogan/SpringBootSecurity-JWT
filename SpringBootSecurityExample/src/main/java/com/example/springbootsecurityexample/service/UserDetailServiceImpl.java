package com.example.springbootsecurityexample.service;

import com.example.springbootsecurityexample.dao.DeveloperRepository;
import com.example.springbootsecurityexample.dao.ProjectRepository;
import com.example.springbootsecurityexample.model.Developer;
import com.example.springbootsecurityexample.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;

@Service
public class UserDetailServiceImpl   implements UserDetailsService {

    @Autowired
    private DeveloperRepository developerRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public void addProject(Project project){
        projectRepository.save(project);
    }

    public List<Project> getProjects(){
        return projectRepository.findAll();
    }

    public void editProject( long id,  Project project){
        Project existingProject = projectRepository.getById((long) id);
        Assert.notNull(existingProject, "Project Not Found.");
        existingProject.setDescription(project.getDescription());
        projectRepository.save(existingProject);
    }

    public void deleteProject(long id){
        projectRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Developer developer = developerRepository.findByUsername(username);
        if (developer == null){
            throw new UsernameNotFoundException(username);
        }

        return new User(developer.getUsername(), developer.getPassword(), Collections.emptyList());
    }
}
