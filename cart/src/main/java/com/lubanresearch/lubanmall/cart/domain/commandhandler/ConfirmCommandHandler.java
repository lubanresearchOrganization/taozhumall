package com.lubanresearch.lubanmall.cart.domain.commandhandler;

import com.lubanmall.orderserviceapi.bean.CreateDealDTO;
import com.lubanmall.orderserviceapi.bean.ProductItemDTO;
import com.lubanresearch.lubanmall.cart.domain.command.ConfirmCommand;
import com.lubanresearch.lubanmall.cart.infrastructure.persistence.db.entity.CartItemEntity;
import com.lubanresearch.lubanmall.cart.infrastructure.persistence.db.mapper.CartItemEntityMapper;
import com.lubanresearch.lubanmall.cart.infrastructure.persistence.db.query.condition.CartItemEntityQueryCondition;
import com.lubanresearch.lubanmall.cart.infrastructure.remote.OrderService;
import com.lubanresearch.lubanmall.common.exception.ServiceException;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hilbertcao on 2018/2/11.
 */
@Component
public class ConfirmCommandHandler {

    @Autowired
    private CartItemEntityMapper cartItemEntityMapper;
    @Autowired
    private OrderService orderService;

    @CommandHandler
    public void handle(ConfirmCommand command)throws ServiceException {

        CartItemEntityQueryCondition queryCondition = new CartItemEntityQueryCondition();
        queryCondition.createCriteria().andProductIdIn(command.getProductIds()).andCustomerIdEqualTo(command.getCustomerId());
        List<CartItemEntity> cartItemEntityList = cartItemEntityMapper.selectByExample(queryCondition);

        CreateDealDTO createDealDTO = new CreateDealDTO();
        createDealDTO.setCustomerId(command.getCustomerId());

        createDealDTO.setItems(cartItemEntityList.stream().map(
                cartItemEntity -> {
                    ProductItemDTO productItemDTO = new ProductItemDTO();
                    productItemDTO.setProductId(cartItemEntity.getProductId());
                    productItemDTO.setProductNum((cartItemEntity.getProductNum()));
                    return  productItemDTO;
                }
        ).collect(Collectors.toList()));
        createDealDTO.setRemarkMap(command.getRemarkMap());
        orderService.createDeal(createDealDTO);

        cartItemEntityMapper.deleteByExample(queryCondition);

    }
}
