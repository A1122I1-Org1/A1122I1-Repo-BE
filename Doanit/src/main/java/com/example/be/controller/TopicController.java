package com.example.be.controller;

import com.example.be.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/topic")
public class TopicController {
@Autowired
    TopicService topicService;

}
