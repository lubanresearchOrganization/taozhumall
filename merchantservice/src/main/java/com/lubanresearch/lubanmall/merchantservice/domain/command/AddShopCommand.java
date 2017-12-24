package com.lubanresearch.lubanmall.merchantservice.domain.command;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by hilbertcao on 2017/12/25.
 */
public class AddShopCommand {

    @NotBlank
    private String name;

    @NotNull
    private Long accountId;

    private String imgUrl;

    private String discription;

    public AddShopCommand(String name, Long accountId, String imgUrl, String discription) {
        this.name = name;
        this.accountId = accountId;
        this.imgUrl = imgUrl;
        this.discription = discription;
    }

    public String getName() {
        return name;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getDiscription() {
        return discription;
    }

}
