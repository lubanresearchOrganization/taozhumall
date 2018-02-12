package com.lubanresearch.lubanmall.orderservice.domain.command;

import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import java.math.BigDecimal;

/**
 * Created by hilbertcao on 2018/2/13.
 */
public class ChangeTotalCommand {

    @AggregateIdentifier
    private Long id;

    private BigDecimal total;

    public ChangeTotalCommand(Long id, BigDecimal total) {
        this.id = id;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getTotal() {
        return total;
    }

}