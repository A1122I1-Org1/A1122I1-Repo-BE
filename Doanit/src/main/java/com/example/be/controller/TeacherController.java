package com.example.be.controller;


import com.example.be.dto.AccountRoleDTO;
import com.example.be.dto.CreateUpdateTeacherDTO;
import com.example.be.dto.ITeacherDto;
import com.example.be.entity.Account;

import com.example.be.entity.Teacher;
import com.example.be.service.ITeacherService;
//import com.example.be.validate.TeacherValidator;
import com.example.be.validate.TeacherValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class TeacherController {

    @Autowired
    private ITeacherService teacherService;

    @GetMapping("/teacher-list")
    public ResponseEntity<Page<ITeacherDto>> getAllTeacher(@RequestParam(defaultValue = "") String find,
                                                       @RequestParam(value = "page") Integer page){
        Page<ITeacherDto> listTeacher = teacherService.getAllTeacher(find, PageRequest.of(page,4));
        if (listTeacher.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(listTeacher, HttpStatus.OK);
    }

    @DeleteMapping("/teacher/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable Integer id){
        teacherService.deleteTeacher(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }










}