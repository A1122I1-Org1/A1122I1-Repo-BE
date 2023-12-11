package com.example.be.controller;

import com.example.be.dto.GroupAccountDTO;
import com.example.be.entity.Account;
import com.example.be.security.UserPrinciple;
import com.example.be.service.IAccountService;
import com.example.be.service.IGroupAccountService;
import com.example.be.validate.GroupAccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/Group")
public class GroupAccountController {
    @Autowired
    IGroupAccountService IGroupAccountService;

    @Autowired
    GroupAccountValidator groupAccountValidator;
    @Autowired
    IAccountService iAccountService;
    @PreAuthorize("hasRole('STUDENT')" )
    @PostMapping("/createGroup")
    public ResponseEntity<?> doCreateGroup(@RequestBody GroupAccountDTO groupAccountDto) {
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account= iAccountService.findByUsername(userPrinciple.getUsername());
        Integer accountId= account.getAccountId();
        Integer studentID= account.getStudent().getStudentId();

        Map<String,String> errors= groupAccountValidator.validate(groupAccountDto);
        if(errors.isEmpty()) {
            IGroupAccountService.saveGroup(groupAccountDto.getName(), studentID, accountId,groupAccountDto.getStudents());
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
        }
    }

//    @PreAuthorize("hasRole('STUDENT')" )
    @GetMapping("/joinGroup")
    public ResponseEntity<?> joinGroup(@RequestParam("studentID") Integer studentId, @RequestParam("groupId") Integer groupId) {
        IGroupAccountService.acceptJoinGroup(groupId, studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
