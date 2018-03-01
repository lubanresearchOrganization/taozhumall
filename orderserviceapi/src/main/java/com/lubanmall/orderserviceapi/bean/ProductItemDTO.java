package com.lubanmall.orderserviceapi.bean;

/**
 * Created by hilbertcao on 2018/3/1.
 */
public class ProductItemDTO {

    private Long productId;
    private Integer productNum;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }
}
