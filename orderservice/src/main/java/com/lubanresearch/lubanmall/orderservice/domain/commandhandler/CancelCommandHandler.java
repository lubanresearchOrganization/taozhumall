package com.lubanresearch.lubanmall.orderservice.domain.commandhandler;

import com.lubanresearch.lubanmall.orderservice.domain.OrderStatus;
import com.lubanresearch.lubanmall.orderservice.domain.bean.Order;
import com.lubanresearch.lubanmall.orderservice.domain.command.CancelCommand;
import com.lubanresearch.lubanmall.orderservice.infrastructure.persistence.db.OrderMapper;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by hilbertcao on 2018/3/4.
 */
@Component
public class CancelCommandHandler {

    @Autowired
    private OrderMapper orderMapper;

    @CommandHandler
    public void handle(CancelCommand command) {

        Order order = new Order();
        order.setId(command.getId());
        order.setStatus(OrderStatus.CANCELED.getValue());
        orderMapper.updateByPrimaryKeySelective(order);
    }

}
