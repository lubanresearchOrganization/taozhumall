package com.lubanresearch.lubanmall.cartapi;

/**
 * Created by hilbertcao on 2018/2/4.
 */
public class ChangeNumDTO {
    private Long productId;
    private Integer num;

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
}
