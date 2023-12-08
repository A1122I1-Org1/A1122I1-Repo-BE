package com.example.be.dto;

import org.springframework.beans.factory.annotation.Value;

public interface IStudentEditDTO {
    Integer getStudentId();
    String getName();
    String getDateOfBirth();
    String getAddress();
    String getPhone();
    String getEmail();
    String getImage();
    Integer getGrade();

    @Value("#{target.gender == 1}")
    Boolean getGender();
}

