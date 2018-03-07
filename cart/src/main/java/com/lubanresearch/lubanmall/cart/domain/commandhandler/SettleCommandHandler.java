package com.lubanresearch.lubanmall.cart.domain.commandhandler;

import com.lubanresearch.lubanmall.cart.domain.command.SettleCommand;
import com.lubanresearch.lubanmall.cart.infrastructure.persistence.db.mapper.CartItemEntityMapper;
import com.lubanresearch.lubanmall.common.exception.ServiceException;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created by hilbertcao on 2018/2/11.
 */
@Component
public class SettleCommandHandler {

    @Autowired
    private CartItemEntityMapper cartItemEntityMapper;

    @CommandHandler
    public void handle(SettleCommand command)throws ServiceException {

    }

}
