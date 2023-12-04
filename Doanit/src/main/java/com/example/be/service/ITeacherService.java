package com.example.be.service;

import com.example.be.dto.ITeacherDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITeacherService {

    Page<ITeacherDto> getAllTeacher(String find, Pageable pageable);
    void deleteTeacher(Integer id);
}
