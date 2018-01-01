package com.lubanresearch.lubanmall.orderservice.domain;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.springframework.context.event.EventListener;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by hilbertcao on 2018/1/2.
 */
@Entity
@Table(name="lb_deal")
public class Deal extends AbstractAnnotatedAggregateRoot<Long> {


    @Id
    @AggregateIdentifier
    private Long id;
    private BigDecimal totalAmount;
    private Long customerId;
    private Date createTime;
    private Byte status;

    @CommandHandler
    public  Deal(CreateDealCommand command) {
        this.id = System.currentTimeMillis();
        this.customerId = command.getCustomerId();
        this.createTime = new Date();
        this.status = (byte)1;
        this.totalAmount = command.getTotalAmount();
        //apply(new CreateDealEvent(this.id,command.getTotalAmount(),command.getCustomerId(),this.createTime,this.status));
    }

    @EventListener
    public void onCreate(CreateDealEvent event){


        System.out.println(event.getId());
    }

}
