package com.lubanresearch.lubanmall.merchantservice.domain.command;


import javax.validation.constraints.NotNull;

/**
 * Created by hilbertcao on 2017/12/25.
 */
public class UpdateShopCommand {

    @NotNull
    private Long id;

    private String name;

    private String imgUrl;

    private String discription;

    public UpdateShopCommand(Long id, String name, String imgUrl, String discription) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.discription = discription;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getDiscription() {
        return discription;
    }
}
