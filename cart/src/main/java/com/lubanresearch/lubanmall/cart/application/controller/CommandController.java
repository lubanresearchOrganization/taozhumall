package com.lubanresearch.lubanmall.cart.application.controller;

import com.lubanresearch.lubanmall.cart.domain.command.AddCartItemCommand;
import com.lubanresearch.lubanmall.cart.domain.command.ChangeNumCommand;
import com.lubanresearch.lubanmall.cart.domain.command.ConfirmCommand;
import com.lubanresearch.lubanmall.cart.domain.command.RemoveCartItemComand;
import com.lubanresearch.lubanmall.cartapi.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

/**
 * Created by hilbertcao on 2018/2/4.
 */
@Controller
@RequestMapping("/v/0.1/carts／{customerId}")
public class CommandController {

    @Autowired
    private CommandGateway commandGateway;

    @RequestMapping(value = "/commands/addCartItem", method = RequestMethod.POST)
    public void addCartItem(@PathVariable("customerId") Long customerId, @RequestBody AddCartItemDTO addCartItemDTO) {


        commandGateway.sendAndWait(
                new AddCartItemCommand(customerId, addCartItemDTO.getProductId()
                        , addCartItemDTO.getNum(), addCartItemDTO.getProductUnitPrice()));

    }

    @RequestMapping(value = "/commands/removeCartItem", method = RequestMethod.DELETE)
    public void removeCartItem(@PathVariable("customerId") Long customerId, @RequestParam("productId") Long productId) {

        commandGateway.sendAndWait(new RemoveCartItemComand(customerId, productId));
    }

    /**
     * 结算
     * 将相关的购物车项状态改成待确认状态
     *
     * @param settleDTO
     */
    @RequestMapping(value = "/commands/settle", method = RequestMethod.POST)
    public void settle(@RequestBody SettleDTO settleDTO) {

    }

    /**
     * 修改购物车项的数量
     *
     * @param changeNumDTO
     */
    @RequestMapping(value = "/commands/changeNum", method = RequestMethod.POST)
    public void changeNum(@PathVariable("customerId") Long customerId, @RequestBody ChangeNumDTO changeNumDTO) {
        commandGateway.sendAndWait(new ChangeNumCommand(customerId,
                changeNumDTO.getProductId(), changeNumDTO.getNum()));
    }

    /**
     * 确认购物车，创建订单，将相关的购物车项状态设置为已确认
     */
    @RequestMapping(value = "/commands/confirm", method = RequestMethod.POST)
    public void confirm(@PathVariable("customerId") Long customerId,@RequestBody ConfirmDTO confirmDTO) {

        commandGateway.sendAndWait(
                new ConfirmCommand(customerId,
                        confirmDTO.getProductIds(),
                        confirmDTO.getExtras().stream().collect(Collectors.toMap(
                                GroupExtraDTO::getId,GroupExtraDTO::getRemark
                        ))
                        )
        );
    }
}
