package com.lubanresearch.lubanmall.merchantservice.domain.command;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by hilbertcao on 2017/12/25.
 */
public class AddProductCommand {

    @NotBlank
    private String name;

    private String imgUrl;

    @NotNull
    private BigDecimal unitPrice;

    @NotNull
    private Long shopId;

    @NotNull
    private Long categoryId;

    public AddProductCommand(String name, String imgUrl, BigDecimal unitPrice, Long shopId, Long categoryId) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.unitPrice = unitPrice;
        this.shopId = shopId;
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public Long getShopId() {
        return shopId;
    }

    public Long getCategoryId() {
        return categoryId;
    }
}
