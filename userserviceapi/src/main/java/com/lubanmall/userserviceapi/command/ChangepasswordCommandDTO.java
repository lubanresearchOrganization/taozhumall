package com.lubanmall.userserviceapi.command;

/**
 * Created by hilbertcao on 2017/12/19.
 */
public class ChangepasswordCommandDTO {
    private Long id;
    private String newPassword;
    private String oldPassword;

    public ChangepasswordCommandDTO(Long id, String newPassword, String oldPassword) {
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
