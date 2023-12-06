package com.example.be.service.impl;

import com.example.be.entity.InfoTopicRegister;
import com.example.be.repository.IInfoTopicRegisterRepository;
import com.example.be.service.IInfoTopicRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InfoTopicRegisterServiceImpl implements IInfoTopicRegisterService {

    @Autowired
    private IInfoTopicRegisterRepository iInfoTopicRegisterRepository;

    @Override
    public Page<InfoTopicRegister> getListTopicNotApproval(Integer idTeacher, Pageable pageable) {
        return iInfoTopicRegisterRepository.findAllTopicNotApprovalByTeacherId(idTeacher, pageable);
    }

    @Override
    public void registerInfoTopic(InfoTopicRegister infoTopicRegister) {
        iInfoTopicRegisterRepository.save(infoTopicRegister);
    }
}
