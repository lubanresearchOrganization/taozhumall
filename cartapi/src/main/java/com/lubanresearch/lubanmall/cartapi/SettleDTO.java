package com.lubanresearch.lubanmall.cartapi;

import java.util.List;

/**
 * Created by hilbertcao on 2018/2/4.
 */
public class SettleDTO {

    private List<AddCartItemDTO> cartItemDTOs;

    public List<AddCartItemDTO> getCartItemDTOs() {
        return cartItemDTOs;
    }

    public void setCartItemDTOs(List<AddCartItemDTO> cartItemDTOs) {
        this.cartItemDTOs = cartItemDTOs;
    }
}
