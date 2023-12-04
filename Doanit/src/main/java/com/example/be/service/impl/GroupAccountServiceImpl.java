package com.example.be.service.impl;

import com.example.be.dto.IAccountGroupDTO;
import com.example.be.entity.GroupAccount;
import com.example.be.repository.IGroupAccountRepository;
import com.example.be.service.IGroupAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GroupAccountServiceImpl implements IGroupAccountService {
   @Autowired
   private IGroupAccountRepository iGroupAccountRepository;

    @Override
    public List<GroupAccount> findAll() {
        return null;
    }

    @Override
    public void deleteGroup(Integer groupId,List<Integer> integerList) {

        iGroupAccountRepository.deleteGroup(groupId);
        for (Integer id:integerList){
            iGroupAccountRepository.deleteGroupOfStudentById(id);
        }
    }

    @Override
    public void acceptGroup(Integer groupId) {
    iGroupAccountRepository.acceptGroup(groupId);
    }

    @Override
    public GroupAccount getGroupById(Integer id) {
        return null;
    }

    @Override
    public Page<GroupAccount> listGroup(Pageable pageable) {
        return iGroupAccountRepository.findAllGroup(pageable);
    }

    @Override
    public void updateDeadLine(String date, Integer groupId) {
         iGroupAccountRepository.updateDeadLine(date, groupId);
    }

    

}
