package com.lubanresearch.lubanmall.orderservice.domain.commandhandler;

import com.lubanresearch.lubanmall.orderservice.domain.OrderStatus;
import com.lubanresearch.lubanmall.orderservice.domain.bean.Order;
import com.lubanresearch.lubanmall.orderservice.domain.command.DeliverCommand;
import com.lubanresearch.lubanmall.orderservice.infrastructure.persistence.db.OrderMapper;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by hilbertcao on 2018/3/4.
 */
@Component
public class DeliverCommandHandler {

    @Autowired
    private OrderMapper orderMapper;

    @CommandHandler
    public void handle(DeliverCommand command) {

        Order order = new Order();
        order.setId(command.getId());
        order.setStatus(OrderStatus.DELIVERED.getValue());
        orderMapper.updateByPrimaryKeySelective(order);
    }
}
