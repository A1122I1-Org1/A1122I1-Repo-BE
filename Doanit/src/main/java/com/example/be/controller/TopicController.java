package com.example.be.controller;

import com.example.be.dto.InfoTopicRegisterDTO;
import com.example.be.entity.InfoTopicRegister;
import com.example.be.entity.Topic;
import com.example.be.service.IGroupAccountService;
import com.example.be.service.IInfoTopicRegisterService;
import com.example.be.service.ITopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/topic")
public class TopicController {
    @Autowired
    ITopicService iTopicService;
    @Autowired
    IInfoTopicRegisterService iInfoTopicRegisterService;
    @Autowired
    IGroupAccountService iGroupAccountService;

    @RequestMapping(value = "/create_topic", method = RequestMethod.POST, consumes = {"application/json"})
    private ResponseEntity<InfoTopicRegisterDTO> registerTopic(@RequestBody InfoTopicRegisterDTO infoTopicRegisterDTO) {
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
    }
}
