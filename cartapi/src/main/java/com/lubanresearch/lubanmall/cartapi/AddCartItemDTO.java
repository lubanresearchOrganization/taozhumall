package com.lubanresearch.lubanmall.cartapi;

import java.math.BigDecimal;

/**
 * Created by hilbertcao on 2018/2/4.
 */
public class AddCartItemDTO {

    private Long productId;
    private Integer num;
    private BigDecimal productUnitPrice;

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

    public BigDecimal getProductUnitPrice() {
        return productUnitPrice;
    }

    public void setProductUnitPrice(BigDecimal productUnitPrice) {
        this.productUnitPrice = productUnitPrice;
    }
}
