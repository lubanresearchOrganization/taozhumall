package com.lubanresearch.lubanmall.cart.domain.commandhandler;

import com.lubanresearch.lubanmall.cart.domain.command.ChangeNumCommand;
import com.lubanresearch.lubanmall.cart.infrastructure.persistence.db.entity.CartItemEntity;
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
public class ChangeNumCommandHandler {

    @Autowired
    private CartItemEntityMapper cartItemEntityMapper;

    @CommandHandler
    public void handle(ChangeNumCommand command)throws ServiceException {


        CartItemEntityQueryCondition cartItemEntityQueryCondition = new CartItemEntityQueryCondition();
        cartItemEntityQueryCondition.createCriteria().andCustomerIdEqualTo(command.getCustomerId())
                .andProductIdEqualTo(command.getProductId());
        CartItemEntity cartItemEntity = new CartItemEntity();
        cartItemEntity.setProductNum(command.getNum());
        cartItemEntityMapper.updateByExampleSelective(cartItemEntity,cartItemEntityQueryCondition);
    }
}
