package com.lubanresearch.lubanmall.orderservice.domain.command;

import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by hilbertcao on 2018/2/13.
 */
public class CancelCommand {
    @AggregateIdentifier
    private Long id;

    public CancelCommand(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
