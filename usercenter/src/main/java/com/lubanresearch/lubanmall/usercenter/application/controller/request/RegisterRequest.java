package com.lubanresearch.lubanmall.usercenter.application.controller.request;

/**
 * Created by hilbertcao on 2018/3/22.
 */
public class RegisterRequest {

    private String name;

    private String password;

    private String mobile;

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
}
