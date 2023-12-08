package com.example.be.service.impl;

import com.example.be.entity.InfoTopicRegister;
import com.example.be.repository.InfoTopicRegisterRepository;
import com.example.be.service.IInfoTopicRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class InfoTopicRegisterServiceImpl implements IInfoTopicRegisterService {
    @Autowired
    InfoTopicRegisterRepository infoTopicRegisterRepository;

    @Override
    public void registerInfoTopic(InfoTopicRegister infoTopicRegister) {
        infoTopicRegisterRepository.save(infoTopicRegister);
    }
}
