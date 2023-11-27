package com.example.be.service;

import com.example.be.entity.GroupAccount;

public interface IGroupAccountService {
void saveGroup(String name,Integer studentId,Integer accountId);
void acceptJoinGroup(Integer groupId, Integer studentId);
GroupAccount findById(Integer groupId);
}
