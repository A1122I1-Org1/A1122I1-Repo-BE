package com.example.be.service.impl;

import com.example.be.entity.Student;
import com.example.be.repository.StudentRepository;
import com.example.be.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IStudentServiceImpl implements IStudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public Page<Student> findAllStudent(String find, Pageable pageable) {
        return studentRepository.getAllStudent(find,pageable);
    }

    @Override
    public Page<Student> findAllStudent(String find, Integer teacherId, Pageable pageable) {
        return studentRepository.getAllStudent(find,teacherId, pageable);
    }
}
