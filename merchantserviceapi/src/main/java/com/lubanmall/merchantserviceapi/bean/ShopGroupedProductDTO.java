package com.lubanmall.merchantserviceapi.bean;

import java.util.List;

/**
 * Created by hilbertcao on 2018/2/18.
 */
public class ShopGroupedProductDTO {


    private ShopDTO shop;
    private List<ProductDTO> products;


    public ShopDTO getShop() {
        return shop;
    }

    public void setShop(ShopDTO shop) {
        this.shop = shop;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
