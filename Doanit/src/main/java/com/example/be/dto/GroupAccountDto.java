package com.example.be.dto;

public class GroupAccountDto {
    Integer id;
    String name;

    public GroupAccountDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public GroupAccountDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
