package com.example.be.dto;

import com.example.be.entity.Faculty;

public interface ITeacherDto {
    Integer getTeacherId();
    String getTeacherName();
    String getEmail();
    String getPhone();
    String getAvatar();
    String getFacultyName();
}
