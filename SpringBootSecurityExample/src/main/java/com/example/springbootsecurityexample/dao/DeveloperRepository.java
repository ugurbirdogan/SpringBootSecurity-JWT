package com.example.springbootsecurityexample.dao;

import com.example.springbootsecurityexample.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloperRepository extends JpaRepository<Developer,Long > {
    Developer findByUsername(String username);
}
