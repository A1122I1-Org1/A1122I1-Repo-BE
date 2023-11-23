package com.example.be.controller;


import com.example.be.dto.AccountRoleDTO;
import com.example.be.dto.CreateUpdateTeacherDTO;
import com.example.be.dto.ITeacherUpdateDTO;
import com.example.be.entity.Account;
import com.example.be.service.IAccountService;
import com.example.be.service.ITeacherService;
//import com.example.be.validate.TeacherValidator;
import com.example.be.validate.TeacherValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private IAccountService accountService;

    private Map<String,String> errors;

    @PostMapping( "/create-teacher")
    public ResponseEntity<?> createTeacher(@RequestBody CreateUpdateTeacherDTO teacherDTO) {
        if (teacherDTO == null) {
            return new ResponseEntity<CreateUpdateTeacherDTO>(HttpStatus.BAD_REQUEST);
        } else {
            errors = TeacherValidator.validate(teacherDTO);
            if (errors.isEmpty()) {
                Account account = new Account();
                account.setUsername(teacherDTO.getEmail());
                account.setPassword("123");
                account = accountService.registerAccount(account);
                teacherDTO.setAccountId(account.getId());

                AccountRoleDTO accountRoleDTO = new AccountRoleDTO();
                accountRoleDTO.setAccountId(account.getId());
                accountRoleDTO.setRoleId(2);
                teacherService.createTeacher(teacherDTO);
                return new ResponseEntity<CreateUpdateTeacherDTO>(teacherDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>( errors, HttpStatus.BAD_REQUEST);
            }
        }
    }


    @GetMapping("/getTeacherById/{id}")
    public ResponseEntity<?> findStudentById(@PathVariable Integer id){
        ITeacherUpdateDTO teacher = teacherService.getTeacherById(id);
        if (teacher == null){
            return new ResponseEntity<>("Giáo viên không tồn tại",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ITeacherUpdateDTO>(teacher, HttpStatus.OK);
    }

//    @PostMapping("")
//    public ResponseEntity<?> updateTeacher
}
