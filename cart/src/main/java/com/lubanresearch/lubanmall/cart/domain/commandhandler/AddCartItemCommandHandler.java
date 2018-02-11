package com.lubanresearch.lubanmall.cart.domain.commandhandler;

import com.lubanresearch.lubanmall.cart.domain.command.AddCartItemCommand;
import com.lubanresearch.lubanmall.cart.infrastructure.persistence.db.entity.CartItemEntity;
import com.lubanresearch.lubanmall.cart.infrastructure.persistence.db.mapper.CartItemEntityMapper;
import com.lubanresearch.lubanmall.common.exception.ServiceException;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by hilbertcao on 2018/2/11.
 */
@Component
public class AddCartItemCommandHandler {

    @Autowired
    private CartItemEntityMapper cartItemEntityMapper;

    @CommandHandler
    public void handle(AddCartItemCommand command)throws ServiceException {


        CartItemEntity cartItemEntity = new CartItemEntity();
        cartItemEntity.setCreateTime(new Date());
        cartItemEntity.setCustomerId(command.getCustomerId());
        cartItemEntity.setProductId(command.getProductId());
        cartItemEntity.setProductNum(command.getNum());
        cartItemEntity.setProductPrice(command.getProductPrice());
        cartItemEntity.setId(System.nanoTime());
        cartItemEntityMapper.insert(cartItemEntity);
    }
}
