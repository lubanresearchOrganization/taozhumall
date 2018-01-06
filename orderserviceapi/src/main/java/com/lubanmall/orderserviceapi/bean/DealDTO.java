package com.lubanmall.orderserviceapi.bean;

import java.util.List;

public class DealDTO {



    private Long customerId;

    private List<OrderDTO> orderList;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }


    public List<OrderDTO> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderDTO> orderList) {
        this.orderList = orderList;
    }
}
