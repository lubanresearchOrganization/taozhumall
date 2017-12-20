package com.lubanresearch.lubanmall.userservice.domain.command;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by hilbertcao on 2017/12/19.
 */
public class ChangepasswordCommand {
    @NotNull
    private Long id;
    @NotBlank
    private String newPassword;
    @NotBlank
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
