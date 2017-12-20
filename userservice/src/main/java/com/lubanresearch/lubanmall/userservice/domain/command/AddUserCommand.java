package com.lubanresearch.lubanmall.userservice.domain.command;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by hilbertcao on 2017/12/19.
 */
public class AddUserCommand {

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    private String mobile;

    @NotNull
    private Byte type;

    public AddUserCommand(String name, String password, String mobile, Byte type) {
        this.name = name;
        this.password = password;
        this.mobile = mobile;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}
