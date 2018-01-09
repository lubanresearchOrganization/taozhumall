package com.lubanresearch.lubanmall.orderservice.domain;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

public class UpdateOrderStatusCommand {



    @TargetAggregateIdentifier
    private Long id;


    private Long orderId;

    private Byte status;


    public UpdateOrderStatusCommand(Long id, Long orderId, Byte status) {
        this.id = id;
        this.orderId = orderId;
        this.status = status;
    }


    public Long getOrderId() {
        return orderId;
    }

    public Byte getStatus() {
        return status;
    }
}
