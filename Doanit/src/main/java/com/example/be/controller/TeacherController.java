package com.example.be.controller;


import com.example.be.dto.AccountRoleDTO;
import com.example.be.dto.CreateUpdateTeacherDTO;
import com.example.be.entity.Account;
import com.example.be.service.IAccountService;
import com.example.be.service.ITeacherService;
//import com.example.be.validate.TeacherValidator;
import com.example.be.validate.TeacherValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private TeacherValidator teacherValidator;

    @RequestMapping(value = "/create-teacher", method = RequestMethod.POST)
    public ResponseEntity<?> createTeacher(@RequestBody CreateUpdateTeacherDTO teacherDTO) {
        if (teacherDTO == null) {
            return new ResponseEntity<CreateUpdateTeacherDTO>(HttpStatus.BAD_REQUEST);
        } else {
            Map<String,String> errors  = teacherValidator.validate(teacherDTO);
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
}