package com.lubanmall.orderserviceapi.bean;

import java.util.List;
import java.util.Map;

/**
 * Created by hilbertcao on 2018/2/11.
 */
public class CreateDealDTO {

    private Long customerId;
    private List<OrderItemDTO> items;

    //key为shopId
    Map<Long,String> remarkMap;


    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }

    public Map<Long, String> getRemarkMap() {
        return remarkMap;
    }

    public void setRemarkMap(Map<Long, String> remarkMap) {
        this.remarkMap = remarkMap;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
