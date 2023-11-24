package com.example.be.controller;

import com.example.be.dto.GroupAccountDto;
import com.example.be.entity.GroupAccount;
import com.example.be.service.GroupAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/Group")
public class GroupAccountController {
        @Autowired
    GroupAccountService groupAccountService;

    @PostMapping("/createGroup")
    public ResponseEntity<GroupAccountDto> doCreateGroup(@RequestBody GroupAccountDto groupAccountDto) {

        groupAccountService.saveGroup(groupAccountDto.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/joinGroup")
    public ResponseEntity<?> joinGroup(@RequestParam("studentID") Integer studentId, @RequestParam("groupId") Integer groupId) {
        groupAccountService.acceptJoinGroup(groupId, studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
