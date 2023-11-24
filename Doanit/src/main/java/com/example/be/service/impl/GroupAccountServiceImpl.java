package com.example.be.service.impl;

import com.example.be.repository.GroupAccountRepository;
import com.example.be.service.GroupAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class GroupAccountServiceImpl implements GroupAccountService {
    @Autowired
    GroupAccountRepository groupAccountRepository;
    @Override
    public void saveGroup(String name) {
        groupAccountRepository.saveGroup(name);
    }

    @Override
    public void acceptJoinGroup(Integer groupId, Integer studentId) {
    groupAccountRepository.agreeJoinGroup(groupId,studentId);
    }
}
