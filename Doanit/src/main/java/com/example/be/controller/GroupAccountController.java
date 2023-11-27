package com.example.be.controller;

import com.example.be.dto.GroupAccountDTO;
import com.example.be.service.IGroupAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Group")
public class GroupAccountController {
        @Autowired
        IGroupAccountService IGroupAccountService;

    @PostMapping("/createGroup")
    public ResponseEntity<GroupAccountDTO> doCreateGroup(@RequestBody GroupAccountDTO groupAccountDto, @RequestParam("studentId") Integer studentID, @RequestParam("accountId") Integer accountId) {

        IGroupAccountService.saveGroup(groupAccountDto.getName(),studentID,accountId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/joinGroup")
    public ResponseEntity<?> joinGroup(@RequestParam("studentID") Integer studentId, @RequestParam("groupId") Integer groupId) {
        IGroupAccountService.acceptJoinGroup(groupId, studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
