package com.example.be.service;

import com.example.be.dto.InfoTopicRegisterDTO;
import com.example.be.entity.InfoTopicRegister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface IInfoTopicRegisterService {
    void registerInfoTopic(InfoTopicRegister infoTopicRegister);

    Page<InfoTopicRegister> getListTopicNotApproval(Integer idTeacher, Pageable pageable);

    void sendStudentApproval(InfoTopicRegisterDTO infoTopicRegisterDTO) throws MessagingException, UnsupportedEncodingException;

    void setStatusApproval(Integer id);
}
