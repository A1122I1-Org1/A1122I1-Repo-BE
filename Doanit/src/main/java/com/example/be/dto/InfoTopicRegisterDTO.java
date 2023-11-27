package com.example.be.dto;

import com.example.be.entity.Topic;

import java.util.List;

public class InfoTopicRegisterDTO {
    private Integer id;
    private Topic topic;
    private Integer groupAccountId;






    public InfoTopicRegisterDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
