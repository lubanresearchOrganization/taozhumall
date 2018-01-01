package com.lubanresearch.lubanmall.orderservice.domain;


import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by hilbertcao on 2018/1/2.
 */
public class CreateDealEvent {


    private Long id;
    private BigDecimal totalAmount;
    private Long customerId;
    private Date createTime;
    private Byte status;

    public CreateDealEvent(Long id, BigDecimal totalAmount, Long customerId, Date createTime, Byte status) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.customerId = customerId;
        this.createTime = createTime;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Byte getStatus() {
        return status;
    }
}
