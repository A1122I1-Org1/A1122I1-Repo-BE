package com.example.be.service;

import com.example.be.dto.IAccountGroupDTO;
import com.example.be.entity.GroupAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IGroupAccountService {
    List<GroupAccount> findAll();
    Page<GroupAccount> listGroup(Pageable pageable);
    void deleteGroup(Integer groupId,List<Integer> integerList);

    void acceptGroup(Integer groupId);
    GroupAccount getGroupById(Integer groupid);
    void updateDeadLine(String date, Integer groupId);

}
