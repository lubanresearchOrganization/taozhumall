package com.lubanresearch.lubanmall.cart.domain.command;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

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
    @NotNull
    private BigDecimal productPrice;

    public AddCartItemCommand(Long customerId, Long productId, Integer num, BigDecimal productPrice) {
        this.customerId = customerId;
        this.productId = productId;
        this.num = num;
        this.productPrice = productPrice;
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

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }
}
