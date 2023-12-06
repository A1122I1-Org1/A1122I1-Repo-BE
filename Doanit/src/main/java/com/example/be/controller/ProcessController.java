package com.example.be.controller;

import com.example.be.entity.InfoTopicRegister;
import com.example.be.service.IInfoTopicRegisterService;
import com.example.be.service.ITopicManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class ProcessController {
    @Autowired
    private IInfoTopicRegisterService iInfoTopicRegisterService;

    @Autowired
    private ITopicManagerService iTopicManagerService;

    @GetMapping("/get-topic-not-approval")
    private ResponseEntity<?> getListTopicNotApproval(@RequestParam("page") int page,
                                                      @RequestParam("size") int size,
                                                      @RequestParam Integer teacherId) {
        Page<InfoTopicRegister> infoTopicRegisterList =
                iInfoTopicRegisterService.getListTopicNotApproval(teacherId, PageRequest.of(page - 1, size));

        if (infoTopicRegisterList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(infoTopicRegisterList, HttpStatus.OK);
    }

    @PostMapping("/approval")
    private ResponseEntity<?> approvalTopic(@RequestBody InfoTopicRegister infoTopicRegister) {
        infoTopicRegister.setStatus(true);
        iInfoTopicRegisterService.registerInfoTopic(infoTopicRegister);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
