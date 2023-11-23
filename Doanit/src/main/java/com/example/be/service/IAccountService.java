package com.example.be.service;

import com.example.be.entity.Account;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public interface IAccountService {
    Account findByUserName(String userName);
    boolean existsByUserName(String userName);
    void changePassword(Account account);
}
