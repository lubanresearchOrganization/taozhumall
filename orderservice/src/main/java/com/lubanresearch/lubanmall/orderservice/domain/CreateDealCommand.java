package com.lubanresearch.lubanmall.orderservice.domain;

import java.util.List;

/**
 * Created by hilbertcao on 2018/1/2.
 */
public class CreateDealCommand {


    private Long customerId;


    private List<Order> orderList;


    public Long getCustomerId() {
        return customerId;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public CreateDealCommand(Long customerId, List<Order> orderList) {

        this.customerId = customerId;
        this.orderList = orderList;
    }

}
