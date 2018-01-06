package com.lubanresearch.lubanmall.orderservice.domain;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "dealId")
    private List<Order> orderList;


    @CommandHandler
    public  Deal(CreateDealCommand command) {
        this.id = System.nanoTime();
        this.customerId = command.getCustomerId();
        this.createTime = new Date();
        this.status = (byte)1;
        this.orderList = command.getOrderList();

        List<Order> orderList = command.getOrderList();

        BigDecimal mTotalAmount = new BigDecimal(0);

        for(Order order : orderList){


            BigDecimal orderTotalAmount = new BigDecimal(0);

            List<OrderItem> orderItemList = order.getOrderItemList();

            for(OrderItem orderItem : orderItemList){


                orderTotalAmount = orderTotalAmount.add(getOrderItemTotalPrice(orderItem));

            }


            order.setTotalAmount(orderTotalAmount);


            mTotalAmount =  mTotalAmount.add(orderTotalAmount);

        }

        this.totalAmount = mTotalAmount;

    }


    private BigDecimal getOrderItemTotalPrice(OrderItem orderItem){

        return orderItem.getUnitPrice().multiply(new BigDecimal(orderItem.getProductNum()));

    }


}
