package com.example.be.service.impl;

import com.example.be.entity.GroupAccount;
import com.example.be.entity.Student;
import com.example.be.repository.GroupAccountRepository;
import com.example.be.service.ClientService;
import com.example.be.service.IGroupAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
public class GroupAccountServiceImpl implements IGroupAccountService {
    @Autowired
    GroupAccountRepository groupAccountRepository;
    @Autowired
    ClientService clientService;
    @Override
    public void saveGroup(String name, Integer studentId, Integer accountId, ArrayList<Student> students) {
        groupAccountRepository.setGroupLeader(accountId);
        GroupAccount grObj = new GroupAccount(name, false);
       GroupAccount groupAccount = groupAccountRepository.save(grObj);
      Boolean b=  clientService.create(groupAccount.getGroupAccountId(),students);
        System.out.println(b);
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
