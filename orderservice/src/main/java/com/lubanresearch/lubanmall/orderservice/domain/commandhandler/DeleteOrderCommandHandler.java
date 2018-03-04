package com.lubanresearch.lubanmall.orderservice.domain.commandhandler;

import com.lubanresearch.lubanmall.orderservice.domain.command.DeleteOrderCommand;
import com.lubanresearch.lubanmall.orderservice.infrastructure.persistence.db.OrderMapper;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by hilbertcao on 2018/3/4.
 */
@Component
public class DeleteOrderCommandHandler {

    @Autowired
    private OrderMapper orderMapper;

    @CommandHandler
    public void handle(DeleteOrderCommand command) {

        orderMapper.deleteByPrimaryKey(command.getOrderId());
    }
}
