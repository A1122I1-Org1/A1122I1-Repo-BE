package com.example.be.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PasswordChangeDTO {
    private String oldPassword, newPassword, confirmPassword;


    public String getOldPassword() {
        return this.oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return this.newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfimPassword() {
        return this.confirmPassword;
    }

    public void setConfimPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
