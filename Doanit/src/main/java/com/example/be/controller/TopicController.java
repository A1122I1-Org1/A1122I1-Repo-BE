package com.example.be.controller;

import com.example.be.dto.InfoTopicRegisterDTO;
import com.example.be.entity.InfoTopicRegister;
import com.example.be.entity.Topic;
import com.example.be.service.IGroupAccountService;
import com.example.be.service.IInfoTopicRegisterService;
import com.example.be.service.ITopicService;
import com.example.be.validate.TopicValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/topic")
public class TopicController {
    @Autowired
    ITopicService iTopicService;
    @Autowired
    IInfoTopicRegisterService iInfoTopicRegisterService;
    @Autowired
    IGroupAccountService iGroupAccountService;
    @Autowired
    TopicValidator topicValidator;

    @RequestMapping(value = "/create_topic", method = RequestMethod.POST, consumes = {"application/json"})
    private ResponseEntity<?> registerTopic(@RequestBody InfoTopicRegisterDTO infoTopicRegisterDTO) {
        Map<String, String> errors = topicValidator.validate(infoTopicRegisterDTO);
        if (errors.isEmpty()) {
            InfoTopicRegister infoTopicRegister = new InfoTopicRegister();
            infoTopicRegister.setTopic(infoTopicRegisterDTO.getTopic());
            infoTopicRegister.setGroupAccount(iGroupAccountService.findById(infoTopicRegisterDTO.getGroupAccountId()));
            if (infoTopicRegister.getTopic().getTopicId() == null) {
                Topic topic = infoTopicRegister.getTopic();
                topic.setDeleteFlag(false);
                infoTopicRegister.setTopic(iTopicService.saveTopic(topic));
            }
            infoTopicRegister.setStatus(false);
            infoTopicRegister.setStatusComplete(false);
            infoTopicRegister.setTopicCancel(false);
            infoTopicRegister.setDescriptionURL(null);
            infoTopicRegister.setTeacher(null);
            infoTopicRegister.setProcessList(null);

            iInfoTopicRegisterService.registerInfoTopic(infoTopicRegister);

            return new ResponseEntity<>(infoTopicRegisterDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

    }
}
