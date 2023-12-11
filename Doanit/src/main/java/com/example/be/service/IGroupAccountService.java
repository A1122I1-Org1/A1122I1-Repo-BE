package com.example.be.service;

import com.example.be.entity.GroupAccount;
import com.example.be.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public interface IGroupAccountService {
void saveGroup(String name, Integer studentId, Integer accountId, ArrayList<Student> students);
void acceptJoinGroup(Integer groupId, Integer studentId);
GroupAccount findById(Integer groupId);

}
