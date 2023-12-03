package com.example.be.service;


import com.example.be.entity.Account;

public interface IAccountService {

    Account findByUsername(String username);

    Account getAccountById(Integer idAccount);


  
    void changePassword(Account account);

    Account registerAccount(Account account);
}
