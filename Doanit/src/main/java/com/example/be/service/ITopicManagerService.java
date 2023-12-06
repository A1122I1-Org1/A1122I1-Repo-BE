package com.example.be.service;

import com.example.be.dto.InfoTopicRegisterDTO;
import com.example.be.dto.TopicProcessDTO;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface ITopicManagerService {
    void topicCancel(Integer id);

    void deleteTopic(Integer id);

    void createTopicProcess(TopicProcessDTO topicProcessDTO);

    void statusInfo(Integer teacherId, Integer id);

    void sendStudent(InfoTopicRegisterDTO infoTopicRegisterDTO) throws MessagingException, UnsupportedEncodingException;
}
