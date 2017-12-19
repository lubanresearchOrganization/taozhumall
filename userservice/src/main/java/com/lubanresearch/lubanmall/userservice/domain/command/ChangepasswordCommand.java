package com.lubanresearch.lubanmall.userservice.domain.command;

/**
 * Created by hilbertcao on 2017/12/19.
 */
public class ChangepasswordCommand {
    private Long id;
    private String newPassword;
    private String oldPassword;

    public ChangepasswordCommand(Long id, String newPassword, String oldPassword) {
        this.id = id;
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
    }

    public Long getId() {
        return id;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }
}
