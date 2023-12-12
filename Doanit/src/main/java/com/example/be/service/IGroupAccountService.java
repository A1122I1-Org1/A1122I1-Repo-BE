package com.example.be.service;

import com.example.be.entity.GroupAccount;
import com.example.be.entity.Student;

import java.util.ArrayList;

public interface IGroupAccountService {
void saveGroup(String name, Integer studentId, Integer accountId, ArrayList<Student> students);
void acceptJoinGroup(Integer groupId, Integer studentId);
GroupAccount findById(Integer groupId);
}
