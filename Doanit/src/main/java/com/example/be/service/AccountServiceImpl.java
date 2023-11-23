package com.example.be.service;


import com.example.be.entity.Account;
import com.example.be.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountServiceImpl implements IAccountService{
    @Autowired
    private IAccountRepository iUserRepository;
    @Override
    public Account findByUserName(String userName) {
        return iUserRepository.findByUsername(userName);
    }

    @Override
    public boolean existsByUserName(String userName) {
        return iUserRepository.existsByUsername(userName);
    }

    @Override
    public void changePassword(Account users) {
      iUserRepository.save(users);
    }
}
