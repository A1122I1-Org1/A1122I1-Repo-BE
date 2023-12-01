package com.example.be.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "account_role")
public class AccountRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_role_id")
    private Integer accountRoleId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private Role role;

    public AccountRole() {
    }

<<<<<<< HEAD
    public AccountRole(Integer id, Account account, Role role) {
        this.accountRoleId = id;
=======
    public AccountRole(Integer accountRoleId, Account account, Role role) {
        this.accountRoleId = accountRoleId;
>>>>>>> main
        this.account = account;
        this.role = role;
    }

    public Integer getAccountRoleId() {
        return accountRoleId;
    }

<<<<<<< HEAD
    public void setAccountRoleId(Integer id) {
        this.accountRoleId = id;
=======
    public void setAccountRoleId(Integer accountRoleId) {
        this.accountRoleId = accountRoleId;
>>>>>>> main
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
<<<<<<< HEAD

}
=======
}
>>>>>>> main
