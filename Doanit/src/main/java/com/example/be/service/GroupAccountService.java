package com.example.be.service;

public interface GroupAccountService {
void saveGroup(String name);
void acceptJoinGroup(Integer groupId, Integer studentId);
}
