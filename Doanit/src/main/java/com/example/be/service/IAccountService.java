package com.example.be.service;


import com.example.be.entity.Account;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional

public interface IAccountService {

    Account findByUsername(String username);

    Account getAccountById(Integer idAccount);

    Account getAccountByIdStudent(Integer id);

    Account getAccountByIdTeacher(Integer id);
  
    void changePassword(Account account);

    Account registerAccount(Account account);

}
