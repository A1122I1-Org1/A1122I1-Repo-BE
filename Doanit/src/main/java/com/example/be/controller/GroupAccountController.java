package com.example.be.controller;

import com.example.be.dto.GroupAccountDTO;
import com.example.be.service.IGroupAccountService;
import com.example.be.validate.GroupAccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @PostMapping("/createGroup")
    public ResponseEntity<?> doCreateGroup(@RequestBody GroupAccountDTO groupAccountDto, @RequestParam("studentId") Integer studentID, @RequestParam("accountId") Integer accountId) {
        Map<String,String> errors= groupAccountValidator.validate(groupAccountDto);
        if(errors.isEmpty()) {
            IGroupAccountService.saveGroup(groupAccountDto.getName(), studentID, accountId);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/joinGroup")
    public ResponseEntity<?> joinGroup(@RequestParam("studentID") Integer studentId, @RequestParam("groupId") Integer groupId) {
        IGroupAccountService.acceptJoinGroup(groupId, studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
