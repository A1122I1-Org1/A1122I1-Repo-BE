package com.example.be.service;

import com.example.be.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IStudentService {
    Page<Student> findAllStudent(String find, Pageable pageable);
    Page<Student> findAllStudent(String find,Integer teacherId, Pageable pageable);
}
