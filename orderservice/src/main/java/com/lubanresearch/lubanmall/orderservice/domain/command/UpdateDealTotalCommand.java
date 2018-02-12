package com.lubanresearch.lubanmall.orderservice.domain.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.math.BigDecimal;


/**
 * Created by hilbertcao on 2018/1/2.
 */
public class UpdateDealTotalCommand {

    @TargetAggregateIdentifier
    private Long id;

    private BigDecimal total;

    public UpdateDealTotalCommand(Long id, BigDecimal total) {
        this.id = id;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
