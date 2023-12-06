package com.example.be.service;

import com.example.be.dto.CreateUpdateTeacherDTO;
import com.example.be.dto.ITeacherUpdateDTO;


public interface ITeacherService {
    void createTeacher(CreateUpdateTeacherDTO createUpdateTeacherDTO);
    ITeacherUpdateDTO getTeacherById(Integer id);
    void updateTeacher(CreateUpdateTeacherDTO createUpdateTeacherDTO);
}
