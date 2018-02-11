package com.lubanresearch.lubanmall.cart.domain.command;

import javax.validation.constraints.NotNull;

/**
 * Created by hilbertcao on 2018/2/8.
 */
public class RemoveCartItemComand {

    @NotNull
    private Long customerId;
    @NotNull
    private Long productId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
