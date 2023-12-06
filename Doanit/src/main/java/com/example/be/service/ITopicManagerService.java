package com.example.be.service;

import com.example.be.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITopicManagerService {
    Page<Topic> findAllTopic(Pageable pageable);

    Page<Topic> findAllTopicByName(String name, Pageable pageable);

    Topic findByIdTopic(Integer id);
}
