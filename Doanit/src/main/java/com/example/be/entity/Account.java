package com.example.be.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    @OneToMany(mappedBy = "account")
    private List<AccountRole> accountRoleList;

    @OneToOne(mappedBy = "account")
    private Teacher teacher;

    @OneToOne(mappedBy = "account")
    private Student student;

    public Account() {
    }

    public Account(Integer id, String username, String password, List<AccountRole> accountRoleList, Teacher teacher, Student student) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.accountRoleList = accountRoleList;
        this.teacher = teacher;
        this.student = student;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AccountRole> getAccountRoleList() {
        return accountRoleList;
    }

    public void setAccountRoleList(List<AccountRole> accountRoleList) {
        this.accountRoleList = accountRoleList;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
