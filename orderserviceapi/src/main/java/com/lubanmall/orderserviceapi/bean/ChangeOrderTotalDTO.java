package com.lubanmall.orderserviceapi.bean;

import java.math.BigDecimal;

/**
 * Created by hilbertcao on 2018/2/4.
 */
public class ChangeOrderTotalDTO {

    private Long orderId;
    private BigDecimal total;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
