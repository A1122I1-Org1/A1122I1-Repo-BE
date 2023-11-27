package com.example.be.service.impl;

import com.example.be.entity.Topic;
import com.example.be.repository.ITopicManagerRepository;
import com.example.be.service.ITopicManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicManagerServiceImpl implements ITopicManagerService {
    @Autowired
    private ITopicManagerRepository topicManagerRepository;

    @Override
    public Page<Topic> findAllTopic(Pageable pageable) {
        return topicManagerRepository.findAllTopic(pageable);
    }

    @Override
    public Page<Topic> findAllTopicBy(String name, Pageable pageable) {
        return topicManagerRepository.findAllTopicBy(false, name, pageable);
    }

    @Override
    public Topic findByIdTopic(Integer id) {
        return topicManagerRepository.findByIdTopic(id);
    }
}
