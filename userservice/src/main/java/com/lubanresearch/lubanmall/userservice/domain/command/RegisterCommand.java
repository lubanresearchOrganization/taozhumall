package com.lubanresearch.lubanmall.userservice.domain.command;

/**
 * Created by caopf on 2017/12/19.
 */
public class RegisterCommand {
    private String name;
    private String password;

    public RegisterCommand() {
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

    public RegisterCommand(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
