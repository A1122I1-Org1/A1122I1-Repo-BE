package com.example.be.service;

import com.example.be.entity.Role;

import javax.transaction.Transactional;


@Transactional
public interface IRoleService {
    Role findByRoleName(String roleName);

}
