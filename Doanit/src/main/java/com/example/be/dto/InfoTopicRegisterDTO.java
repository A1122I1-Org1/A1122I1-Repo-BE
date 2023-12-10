package com.example.be.dto;

import com.example.be.entity.Topic;

public class InfoTopicRegisterDTO {
    private Integer infoTopicResgitor;
    private Topic topic;
    private Integer groupAccountId;






    public InfoTopicRegisterDTO() {
    }

    public Integer getInfoTopicResgitor() {
        return infoTopicResgitor;
    }

    public void setInfoTopicResgitor(Integer infoTopicResgitor) {
        this.infoTopicResgitor = infoTopicResgitor;
    }
    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Integer getGroupAccountId() {
        return groupAccountId;
    }

    public void setGroupAccountId(Integer groupAccountId) {
        this.groupAccountId = groupAccountId;
    }




}
