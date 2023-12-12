package com.example.be.service;


import com.example.be.entity.Student;

import java.util.ArrayList;

public interface ClientService {
    Boolean create(Integer idGroup,ArrayList<Student> students);
}
