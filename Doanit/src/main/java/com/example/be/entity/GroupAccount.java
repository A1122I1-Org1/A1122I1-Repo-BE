package com.example.be.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
@Table(name = "group_account")
public class GroupAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="group_account_id")
    private Integer groupAccountId;

    private String name;
    @Column(name="delete_flag")
    private Boolean deleteFlag;

    private Boolean status;

    @Column(columnDefinition = "DATE")
    private String date;

    @OneToMany(mappedBy = "groupAccount")
    private List<Student> studentList;

    @OneToMany(mappedBy = "groupAccount")
    private List<InfoTopicRegister> infoTopicRegisterList;

    public GroupAccount() {
    }

    public GroupAccount(String name, Boolean deleteFlag) {
        this.name = name;
        this.deleteFlag = deleteFlag;
    }

    public GroupAccount(Integer groupAccountId, String name, Boolean deleteFlag, Boolean status, String date, List<Student> studentList, List<InfoTopicRegister> infoTopicRegisterList) {
        this.groupAccountId = groupAccountId;
        this.name = name;
        this.deleteFlag = deleteFlag;
        this.status = status;
        this.date = date;
        this.studentList = studentList;
        this.infoTopicRegisterList = infoTopicRegisterList;
    }

    public Integer getGroupAccountId() {
        return groupAccountId;
    }

    public void setGroupAccountId(Integer id) {
        this.groupAccountId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isDelete_flag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean delete_flag) {
        this.deleteFlag = delete_flag;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<InfoTopicRegister> getInfoTopicRegisterList() {
        return infoTopicRegisterList;
    }

    public void setInfoTopicRegisterList(List<InfoTopicRegister> infoTopicRegisterList) {
        this.infoTopicRegisterList = infoTopicRegisterList;
    }
}
