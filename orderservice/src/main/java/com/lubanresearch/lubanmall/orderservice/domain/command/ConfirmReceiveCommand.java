package com.lubanresearch.lubanmall.orderservice.domain.command;

import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by hilbertcao on 2018/2/13.
 */
public class ConfirmReceiveCommand {

    @AggregateIdentifier
    private Long id;

    public ConfirmReceiveCommand(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
