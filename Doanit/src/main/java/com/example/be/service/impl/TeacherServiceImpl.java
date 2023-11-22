package com.example.be.service.impl;

import com.example.be.dto.CreateUpdateTeacherDTO;
import com.example.be.dto.ITeacherUpdateDTO;
import com.example.be.repository.TeacherRepository;
import com.example.be.service.ITeacherService;
import com.example.be.validate.TeacherValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService implements ITeacherService {

    @Autowired
    private TeacherRepository teacherRepository;
    @Override
    public void createTeacher(CreateUpdateTeacherDTO createUpdateTeacherDTO) {
        TeacherValidator.validate(createUpdateTeacherDTO);
        teacherRepository.createTeacher(createUpdateTeacherDTO.getAddress(),createUpdateTeacherDTO.getAvatar(), createUpdateTeacherDTO.getDateOfBirth(), createUpdateTeacherDTO.getEmail(), createUpdateTeacherDTO.getName(), createUpdateTeacherDTO.getPhone(),createUpdateTeacherDTO.getDegreeId(),createUpdateTeacherDTO.getFacultyId(),createUpdateTeacherDTO.getAccountId(),createUpdateTeacherDTO.getGender());
    }

    @Override
    public ITeacherUpdateDTO getTeacherById(Integer id) {
        return teacherRepository.getTeacherById(id);
    }

    @Override
    public void updateTeacher(CreateUpdateTeacherDTO createUpdateTeacherDTO) {
        teacherRepository.updateTeacher
                (createUpdateTeacherDTO.getAddress(),createUpdateTeacherDTO.getAvatar(), createUpdateTeacherDTO.getDateOfBirth(), createUpdateTeacherDTO.getEmail(), createUpdateTeacherDTO.getName(), createUpdateTeacherDTO.getPhone(),createUpdateTeacherDTO.getDegreeId(),createUpdateTeacherDTO.getFacultyId(),createUpdateTeacherDTO.getGender(),createUpdateTeacherDTO.getId());
    }
}
