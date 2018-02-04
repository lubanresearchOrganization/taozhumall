package com.lubanresearch.lubanmall.cartapi;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by hilbertcao on 2018/2/4.
 */
public class CartDTO {

    List<GroupDTO> groups;
    private Long customerId;
    private BigDecimal amount;

    public List<GroupDTO> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupDTO> groups) {
        this.groups = groups;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
