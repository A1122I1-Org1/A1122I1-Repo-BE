package com.example.be.controller;

import com.example.be.dto.InfoTopicRegisterDTO;
import com.example.be.dto.TopicProcessDTO;
import com.example.be.entity.Topic;
import com.example.be.service.ITopicManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    ITopicManagerService topicManagerService;

    @PreAuthorize("hasRole('TEACHER')")
    @RequestMapping(value = "/topic", method = RequestMethod.GET)
    public ResponseEntity<Page<Topic>> pageTopic(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size) {
        Page<Topic> topics = topicManagerService.findAllTopic(PageRequest.of(page, size));
        if (topics.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(topics, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @RequestMapping(value = "/topic-search", method = RequestMethod.GET, params = {"page", "size"})
    public ResponseEntity<Page<Topic>> pageTopicFind(@RequestParam(defaultValue = "") String name,
                                                     @RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "size", defaultValue = "20") int size) {
        Page<Topic> topics = topicManagerService.findAllTopicByName(name, PageRequest.of(page, size));
        if (topics.isEmpty()) {
            return new ResponseEntity<Page<Topic>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<Topic>>(topics, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Topic> findTopicById(@PathVariable("id") Integer id) {
        Topic topic = topicManagerService.findByIdTopic(id);
        if (topic == null) {
            return new ResponseEntity<Topic>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Topic>(topic, HttpStatus.OK);
    }

    @RequestMapping(value = "/cancel-topic", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteTopic(@Valid @RequestBody InfoTopicRegisterDTO infoTopicRegisterDTO, UriComponentsBuilder ucBuilder) throws UnsupportedEncodingException, MessagingException {
        topicManagerService.sendStudent(infoTopicRegisterDTO);
        topicManagerService.deleteTopic(infoTopicRegisterDTO.getTopicId());
        topicManagerService.topicCancel(infoTopicRegisterDTO.getInfoTopicRegisterId());
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/create-process", method = RequestMethod.POST)
    public ResponseEntity<Void> createStudent(@Valid @RequestBody InfoTopicRegisterDTO infoTopicRegisterDTO, UriComponentsBuilder ucBuilder) {
        for (TopicProcessDTO topicProcessDTO : infoTopicRegisterDTO.getTopicProcessList()) {
            topicManagerService.createTopicProcess(topicProcessDTO);
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}
