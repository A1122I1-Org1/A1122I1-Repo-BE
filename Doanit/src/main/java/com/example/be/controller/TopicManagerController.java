package com.example.be.controller;

import com.example.be.dto.InfoTopicRegisterDTO;
import com.example.be.dto.NotificationDTO;
import com.example.be.dto.TopicProcessDTO;
import com.example.be.service.ICommentPostService;
import com.example.be.service.IInfoTopicRegisterService;
import com.example.be.service.ITopicManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/public/topic-manager")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TopicManagerController {
    @Autowired
    private IInfoTopicRegisterService iInfoTopicRegisterService;

    @Autowired
    private ITopicManagerService iTopicManagerService;

    @Autowired
    private ICommentPostService iCommentPostService;

    @RequestMapping(value = "/cancel-topic", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteTopic(@Valid @RequestBody InfoTopicRegisterDTO infoTopicRegisterDTO, UriComponentsBuilder ucBuilder) throws UnsupportedEncodingException, MessagingException {
        iTopicManagerService.deleteTopic(infoTopicRegisterDTO.getTopicId());
        iTopicManagerService.topicCancel(infoTopicRegisterDTO.getInfoTopicRegisterId());
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/create-process", method = RequestMethod.POST)
    public ResponseEntity<Void> createStudent(@Valid @RequestBody InfoTopicRegisterDTO infoTopicRegisterDTO, UriComponentsBuilder ucBuilder) {
        for (TopicProcessDTO topicProcessDTO : infoTopicRegisterDTO.getTopicProcessList()) {
            iTopicManagerService.createTopicProcess(topicProcessDTO);
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}
