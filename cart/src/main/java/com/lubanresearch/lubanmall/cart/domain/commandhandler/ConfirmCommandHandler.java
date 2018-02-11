package com.lubanresearch.lubanmall.cart.domain.commandhandler;

import com.lubanresearch.lubanmall.cart.domain.command.ConfirmCommand;
import com.lubanresearch.lubanmall.cart.infrastructure.persistence.db.mapper.CartItemEntityMapper;
import com.lubanresearch.lubanmall.common.exception.ServiceException;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by hilbertcao on 2018/2/11.
 */
public class ConfirmCommandHandler {

    @Autowired
    private CartItemEntityMapper cartItemEntityMapper;

    @CommandHandler
    public void handle(ConfirmCommand command)throws ServiceException {

    }
}
