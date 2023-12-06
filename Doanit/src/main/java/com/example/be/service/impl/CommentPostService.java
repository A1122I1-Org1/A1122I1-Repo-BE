package com.example.be.service.impl;

import com.example.be.dto.NotificationDTO;
import com.example.be.repository.INotificationManagementRepository;
import com.example.be.service.ICommentPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentPostService implements ICommentPostService {
    @Autowired
    private INotificationManagementRepository iNotificationManagementRepository;

    @Override
    public void createNotificationUrl(NotificationDTO notificationDTO) {
        iNotificationManagementRepository.createNotificationUrl(notificationDTO.getContent(), false, LocalDateTime.now().toString(), notificationDTO.getTitle(), notificationDTO.getAccountId(), notificationDTO.getAccountSendNotificationId(), notificationDTO.getUrl());
    }
}
