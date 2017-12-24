package com.lubanmall.userserviceapi.command;

/**
 * Created by hilbertcao on 2017/12/19.
 */
public class ChangepasswordCommandDTO {
    private String newPassword;
    private String oldPassword;

    public ChangepasswordCommandDTO( String newPassword, String oldPassword) {
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
    }

    public ChangepasswordCommandDTO() {
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }
}
