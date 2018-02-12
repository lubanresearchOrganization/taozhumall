package com.lubanresearch.lubanmall.orderservice.domain.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;


/**
 * Created by hilbertcao on 2018/1/9.
 */
public class UpdateDealStatusCommand {



    @TargetAggregateIdentifier
    private Long id;


    private Byte status;


    public UpdateDealStatusCommand(Long id, Byte status) {
        this.id = id;
        this.status = status;
    }

    public Byte getStatus() {
        return status;
    }



}
