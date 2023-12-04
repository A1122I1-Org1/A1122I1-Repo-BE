package com.example.be.dto;

public class GroupAccountDTO {
    Integer id;
    String name;

    public GroupAccountDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public GroupAccountDTO() {
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
