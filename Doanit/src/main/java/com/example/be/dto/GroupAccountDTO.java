package com.example.be.dto;

public class GroupAccountDTO {
    Integer groupaccountId;
    String name;

    public GroupAccountDTO(Integer id, String name) {
        this.groupaccountId = id;
        this.name = name;
    }

    public GroupAccountDTO() {
    }

    public Integer getId() {
        return groupaccountId;
    }

    public void setId(Integer id) {
        this.groupaccountId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
