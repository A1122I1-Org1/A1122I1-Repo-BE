package com.example.be.service.impl;

import com.example.be.entity.GroupAccount;
import com.example.be.entity.Student;
import com.example.be.repository.GroupAccountRepository;
import com.example.be.service.ClientService;
import com.example.be.service.IGroupAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GroupAccountServiceImpl implements IGroupAccountService {
    @Autowired
    GroupAccountRepository groupAccountRepository;
    @Autowired
    ClientService clientService;
    @Autowired
    JavaMailSender javaMailSender;
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

