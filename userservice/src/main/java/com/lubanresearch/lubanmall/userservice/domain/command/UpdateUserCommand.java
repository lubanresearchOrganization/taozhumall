package com.lubanresearch.lubanmall.userservice.domain.command;

/**
 * Created by hilbertcao on 2018/3/25.
 */
public class UpdateUserCommand {

    private Long id;

    private String name;

    private String mobile;

    public UpdateUserCommand(Long id, String name, String mobile) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
