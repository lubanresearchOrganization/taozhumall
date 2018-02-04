package com.lubanresearch.lubanmall.cartapi;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by hilbertcao on 2018/2/4.
 */
public class GroupDTO {

    private Long id;
    private String name;
    private BigDecimal amount;
    private List<CartItemDTO> cartItems;

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<CartItemDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDTO> cartItems) {
        this.cartItems = cartItems;
    }
}
