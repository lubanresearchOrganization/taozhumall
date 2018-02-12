package com.lubanresearch.lubanmall.orderservice.domain.command;

import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by hilbertcao on 2018/2/13.
 */
public class DeliverCommand {

    @AggregateIdentifier
    private Long id;

    public DeliverCommand(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
