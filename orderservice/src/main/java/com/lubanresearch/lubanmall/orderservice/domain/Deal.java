package com.lubanresearch.lubanmall.orderservice.domain;

import com.lubanmall.merchantserviceapi.bean.ProductDTO;
import com.lubanresearch.lubanmall.orderservice.domain.command.*;
import com.lubanresearch.lubanmall.orderservice.infrastructure.constants.Constants;
import com.lubanresearch.lubanmall.orderservice.infrastructure.remote.MerchantService;
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
@Table(name = "lb_deal")
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


    public Deal() {
    }

    @CommandHandler
    public Deal(CreateDealCommand command, MerchantService merchantService) {
        this.id = System.nanoTime();
        this.customerId = command.getCustomerId();
        this.createTime = new Date();
        this.status = Constants.OBLIGATION;
        this.orderList = command.getOrderList();

        List<Order> orderList = command.getOrderList();

        BigDecimal mTotalAmount = new BigDecimal(0);

        for (Order order : orderList) {

            order.setCreateTime(this.createTime);
            order.setCustomerId(this.customerId);
            BigDecimal orderTotalAmount = new BigDecimal(0);

            List<OrderItem> orderItemList = order.getOrderItemList();

            for (OrderItem orderItem : orderItemList) {
                ProductDTO product = merchantService.getProduct(orderItem.getProductId());
                orderItem.setUnitPrice(product.getUnitPrice());
                orderItem.setCreateTime(this.createTime);
                orderTotalAmount = orderTotalAmount.add(getOrderItemTotalPrice(orderItem));

            }


            order.setTotalAmount(orderTotalAmount);

            order.setStatus(OrderStatus.CREATED.getValue());
            mTotalAmount = mTotalAmount.add(orderTotalAmount);

        }

        this.totalAmount = mTotalAmount;

    }


    @CommandHandler
    public void updateTotal(UpdateDealTotalCommand command) {

        this.totalAmount = command.getTotal();
    }


    @CommandHandler
    public void updateDealStatus(UpdateDealStatusCommand command) {

        this.status = command.getStatus();
    }


    @CommandHandler
    public void updateOrderStatus(UpdateOrderStatusCommand command) {

        Long orderId = command.getOrderId();

        for (Order order : this.orderList) {

            if (order.getId().equals(orderId)) {

                order.setStatus(command.getStatus());

            }

        }
    }


    @CommandHandler
    public void deteleDeal(DeteleDealCommand command) {

        markDeleted();

    }


//    @CommandHandler
//    public void deteleOrder(DeleteOrderCommand command) {
//
//        Long orderId = command.getOrderId();
//
//        for (Order order : this.orderList) {
//
//            if (order.getId().equals(orderId)) {
//
//                order.deleteOrder();
//                return;
//
//            }
//
//        }
//
//    }


    private BigDecimal getOrderItemTotalPrice(OrderItem orderItem) {

        return orderItem.getUnitPrice().multiply(new BigDecimal(orderItem.getProductNum()));

    }


}
