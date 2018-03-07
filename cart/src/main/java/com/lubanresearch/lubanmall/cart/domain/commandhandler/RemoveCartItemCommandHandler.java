package com.lubanresearch.lubanmall.cart.domain.commandhandler;

import com.lubanresearch.lubanmall.cart.domain.command.RemoveCartItemComand;
import com.lubanresearch.lubanmall.cart.infrastructure.persistence.db.mapper.CartItemEntityMapper;
import com.lubanresearch.lubanmall.cart.infrastructure.persistence.db.query.condition.CartItemEntityQueryCondition;
import com.lubanresearch.lubanmall.common.exception.ServiceException;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by hilbertcao on 2018/2/11.
 */
@Component
public class RemoveCartItemCommandHandler {
    @Autowired
    private CartItemEntityMapper cartItemEntityMapper;

    @CommandHandler
    public void handle(RemoveCartItemComand command)throws ServiceException {


        CartItemEntityQueryCondition cartItemEntityQueryCondition = new CartItemEntityQueryCondition();
        cartItemEntityQueryCondition.createCriteria().andCustomerIdEqualTo(command.getCustomerId())
                .andProductIdEqualTo(command.getProductId());

        cartItemEntityMapper.deleteByExample(cartItemEntityQueryCondition);
    }
}
