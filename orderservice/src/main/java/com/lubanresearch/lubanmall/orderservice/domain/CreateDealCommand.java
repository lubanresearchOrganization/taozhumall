package com.lubanresearch.lubanmall.orderservice.domain;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import java.math.BigDecimal;

/**
 * Created by hilbertcao on 2018/1/2.
 */
public class CreateDealCommand {


    private BigDecimal totalAmount;
    private Long customerId;

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public CreateDealCommand(BigDecimal totalAmount, Long customerId) {
        this.totalAmount = totalAmount;
        this.customerId = customerId;
    }

}
