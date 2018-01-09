package com.lubanresearch.lubanmall.orderservice.domain;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

public class DeleteOrderCommand {

    @TargetAggregateIdentifier
    private Long id;


    private Long orderId;


    public DeleteOrderCommand(Long id, Long orderId) {
        this.id = id;
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }
}
