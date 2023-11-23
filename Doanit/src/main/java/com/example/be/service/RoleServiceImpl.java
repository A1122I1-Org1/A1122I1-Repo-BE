package com.example.be.service;

import com.example.be.entity.Role;
import com.example.be.repository.IRoleRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class RoleServiceImpl implements IRoleService{
    @Autowired
    private IRoleRepository iRoleRepository;

    @Override
    public Role findByRoleName(String roleName) {
        return iRoleRepository.findByRoleName(roleName);
    }
}
