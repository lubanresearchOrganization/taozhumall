package com.lubanresearch.lubanmall.orderservice.domain.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

public class DeteleDealCommand {



    @TargetAggregateIdentifier
    private Long id;

    public DeteleDealCommand(Long id) {
        this.id = id;
    }


}
