package com.example.be.service.impl;

import com.example.be.dto.ITeacherDto;
import com.example.be.repository.TeacherRepository;
import com.example.be.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements ITeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Page<ITeacherDto> getAllTeacher(String find, Pageable pageable) {
        return teacherRepository.getAllTeacher(find, pageable);
    }

    @Override
    public void deleteTeacher(Integer id) {
        teacherRepository.deleteTeacher(id);
    }
}
