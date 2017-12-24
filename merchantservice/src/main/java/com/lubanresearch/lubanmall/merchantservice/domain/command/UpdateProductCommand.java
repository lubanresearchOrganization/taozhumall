package com.lubanresearch.lubanmall.merchantservice.domain.command;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by hilbertcao on 2017/12/25.
 */
public class UpdateProductCommand {

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    private String imgUrl;

    @NotNull
    private BigDecimal unitPrice;

    @NotNull
    private Long categoryId;

    public UpdateProductCommand(Long id, String name, String imgUrl, BigDecimal unitPrice, Long categoryId) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.unitPrice = unitPrice;
        this.categoryId = categoryId;
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

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public Long getCategoryId() {
        return categoryId;
    }
}
