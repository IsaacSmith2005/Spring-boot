package com.example.studentmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.studentmanager.model.Student;

@Repository
public interface StudentRepository 
        extends JpaRepository<Student, Integer> {
        List<Student> findByNameContainingIgnoreCase(String name);
}

