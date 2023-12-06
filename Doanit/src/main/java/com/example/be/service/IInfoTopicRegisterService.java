package com.example.be.service;

import com.example.be.entity.InfoTopicRegister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IInfoTopicRegisterService {
    Page<InfoTopicRegister> getListTopicNotApproval(Integer idTeacher, Pageable pageable);

    void registerInfoTopic(InfoTopicRegister infoTopicRegister);
}
