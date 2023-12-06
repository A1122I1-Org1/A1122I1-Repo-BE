package com.example.be.dto;

public class NotificationDTO {
    Integer notificationId;
    String timeNotification;
    String content;
    String title;
    Boolean status;
    Integer accountId;
    Integer accountSendNotificationId;
    String url;

    public NotificationDTO() {
    }

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public String getTimeNotification() {
        return timeNotification;
    }

    public void setTimeNotification(String timeNotification) {
        this.timeNotification = timeNotification;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getAccountSendNotificationId() {
        return accountSendNotificationId;
    }

    public void setAccountSendNotificationId(Integer accountSendNotificationId) {
        this.accountSendNotificationId = accountSendNotificationId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
