package com.lubanresearch.lubanmall.cart.domain.command;

import javax.validation.constraints.NotNull;

/**
 * Created by hilbertcao on 2018/2/8.
 */
public class AddCartItemCommand {
    @NotNull
    private Long customerId;
    @NotNull
    private Long productId;
    @NotNull
    private Integer num;

    public AddCartItemCommand(Long customerId, Long productId, Integer num) {
        this.customerId = customerId;
        this.productId = productId;
        this.num = num;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
