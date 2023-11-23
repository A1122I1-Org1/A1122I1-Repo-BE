package com.example.be.service;

<<<<<<< HEAD
import com.example.be.entity.Account;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public interface IAccountService {
    Account findByUserName(String userName);
    boolean existsByUserName(String userName);
    void changePassword(Account account);
=======

import com.example.be.entity.Account;

public interface IAccountService {

    Account findByUsername(String username);

    Account getAccountById(Integer idAccount);

    Account getAccountByIdStudent(Integer id);

    Account getAccountByIdTeacher(Integer id);
  
    void changePassword(Account account);

    Account registerAccount(Account account);
>>>>>>> ad70da6e7688d82d7bb099afd5947747d9a7c490
}
