package com.example.be.service.impl;

import com.example.be.entity.GroupAccount;
import com.example.be.repository.GroupAccountRepository;
import com.example.be.service.IGroupAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class GroupAccountServiceImpl implements IGroupAccountService {
    @Autowired
    GroupAccountRepository groupAccountRepository;
    @Override
    public void saveGroup(String name,Integer studentId,Integer accountId) {
        groupAccountRepository.setGroupLeader(accountId);
        GroupAccount grObj = new GroupAccount(name, false);
       GroupAccount groupAccount = groupAccountRepository.save(grObj);
//        List<GroupAccount> groupAccount= groupAccountRepository.findByName(name);
        groupAccountRepository.agreeJoinGroup(groupAccount.getGroupAccountId(),studentId);
    }

    @Override
    public void acceptJoinGroup(Integer groupId, Integer studentId) {
    groupAccountRepository.agreeJoinGroup(groupId,studentId);
    }

    @Override
    public GroupAccount findById(Integer groupId) {
        return groupAccountRepository.findById(groupId).orElse(null);
    }
}
