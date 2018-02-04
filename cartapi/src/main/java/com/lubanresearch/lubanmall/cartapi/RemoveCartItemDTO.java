package com.lubanresearch.lubanmall.cartapi;

/**
 * Created by hilbertcao on 2018/2/4.
 */
public class RemoveCartItemDTO {

    private Long customerId;
    private Long productId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
