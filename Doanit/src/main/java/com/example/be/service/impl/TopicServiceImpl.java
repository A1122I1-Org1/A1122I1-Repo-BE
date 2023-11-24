package com.example.be.service.impl;


import com.example.be.repository.TopicRepository;
import com.example.be.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TopicServiceImpl implements TopicService {
    @Autowired
    TopicRepository topicRepository;
    @Override
    public void saveTopic(String name, String introduce, String image, String content, Integer facultyId) {
        topicRepository.saveTopic(name, introduce, image, content, facultyId);
    }
}
