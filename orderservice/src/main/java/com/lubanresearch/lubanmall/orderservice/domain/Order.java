package com.lubanresearch.lubanmall.orderservice.domain;


import com.lubanresearch.lubanmall.orderservice.infrastructure.constants.Constants;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by zyf on 2018/1/5
 */
@Entity
@Table(name = "lb_order")
public class Order extends AbstractAnnotatedAggregateRoot<Long> {

    @Id
    private Long id;
    private Date createTime;
    private Long customerId;
    private Long dealId;
    private String remark;
    private Long shopId;
    private Byte status;
    private BigDecimal totalAmount;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "orderId")
    private List<OrderItem> orderItemList;


    public Order(Long customerId, String remark, Long shopId, List<OrderItem> orderItemList) {
        this.id = System.nanoTime();
        this.customerId = customerId;
        this.remark = remark;
        this.shopId = shopId;
        this.status = Constants.WAIT_FOR_DELIVERY;
        this.orderItemList = orderItemList;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getDealId() {
        return dealId;
    }

    public void setDealId(Long dealId) {
        this.dealId = dealId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
