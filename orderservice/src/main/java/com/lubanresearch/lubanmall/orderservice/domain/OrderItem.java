package com.lubanresearch.lubanmall.orderservice.domain;


import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zyf on 2018/1/5
 */
@Entity
@Table(name = "lb_order_item")
public class OrderItem extends AbstractAnnotatedAggregateRoot<Long> {


    @Id
    private Long id;
    private Long orderId;
    private Long productId;
    private BigDecimal unitPrice;
    @NotNull
    private Integer productNum;
    private Date createTime;


    public OrderItem(){

    }

    public OrderItem( Long productId, Integer productNum) {
        this.id = System.nanoTime();
        this.createTime = new Date();
        this.orderId = orderId;
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.productNum = productNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
