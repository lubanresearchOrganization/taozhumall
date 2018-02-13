package com.lubanresearch.lubanmall.cart.domain.commandhandler;

import com.lubanresearch.lubanmall.cart.domain.command.AddCartItemCommand;
import com.lubanresearch.lubanmall.cart.infrastructure.persistence.db.entity.CartItemEntity;
import com.lubanresearch.lubanmall.cart.infrastructure.persistence.db.mapper.CartItemEntityMapper;
import com.lubanresearch.lubanmall.cart.infrastructure.persistence.db.query.condition.CartItemEntityQueryCondition;
import com.lubanresearch.lubanmall.cart.infrastructure.remote.MerchantService;
import com.lubanresearch.lubanmall.common.exception.ServiceException;
import org.apache.commons.collections.CollectionUtils;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by hilbertcao on 2018/2/11.
 */
@Component
public class AddCartItemCommandHandler {

    @Autowired
    private CartItemEntityMapper cartItemEntityMapper;

    @Autowired
    private MerchantService merchantService;


    @CommandHandler
    public synchronized void handle(AddCartItemCommand command)throws ServiceException {

        CartItemEntityQueryCondition cartItemEntityQueryCondition = new CartItemEntityQueryCondition();
        cartItemEntityQueryCondition.createCriteria().andCustomerIdEqualTo(command.getCustomerId())
                .andProductIdEqualTo(command.getProductId());
        List<CartItemEntity> cartItemEntityList = cartItemEntityMapper.selectByExample(cartItemEntityQueryCondition);
        if(CollectionUtils.isEmpty(cartItemEntityList)){
            CartItemEntity cartItemEntity = new CartItemEntity();
            cartItemEntity.setCreateTime(new Date());
            cartItemEntity.setCustomerId(command.getCustomerId());
            cartItemEntity.setProductId(command.getProductId());
            cartItemEntity.setProductNum(command.getNum());
            cartItemEntity.setProductPrice(merchantService.getProduct(command.getProductId()).getUnitPrice());
            cartItemEntity.setId(System.nanoTime());
            cartItemEntityMapper.insert(cartItemEntity);
        }else{
            CartItemEntity cartItemEntit = cartItemEntityList.get(0);
            cartItemEntit.setProductNum(cartItemEntit.getProductNum()+command.getNum());
            cartItemEntityMapper.updateByPrimaryKeySelective(cartItemEntit);
        }

    }
}
