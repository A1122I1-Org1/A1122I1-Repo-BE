package com.example.be.service;


import com.example.be.entity.Account;

import javax.transaction.Transactional;


@Transactional
public interface IAccountService {
    Account findByUserName(String userName);

    boolean existsByUserName(String userName);

    void changePassword(Account account);

    Account findByUsername(String username);

    Account getAccountById(Integer idAccount);

    Account getAccountByIdStudent(Integer id);

    Account getAccountByIdTeacher(Integer id);

    Account registerAccount(Account account);

}
