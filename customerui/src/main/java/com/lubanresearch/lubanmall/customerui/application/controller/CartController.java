package com.lubanresearch.lubanmall.customerui.application.controller;

import com.lubanresearch.lubanmall.cartapi.*;
import com.lubanresearch.lubanmall.customerui.infrastructure.remote.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hilbertcao on 2018/2/3.
 */
@Controller
@RequestMapping("/v/0.1/carts/{customerId}")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping("/")
    public @ResponseBody
    CartDTO getCustomerCart(@PathVariable("customerId") Long customerId){

        return cartService.getCustomerCart(customerId);
    }

    @RequestMapping("／toBeConfirmed")
    public @ResponseBody CartDTO getToBeConfirmedCustomerCart(@PathVariable("customerId") Long customerId){

        return cartService.getToBeConfirmedCustomerCart(customerId);
    }

    @RequestMapping(value="/commands/addCartItem",method = RequestMethod.POST)
    public void addCartItem(@RequestBody AddCartItemDTO addCartItemDTO){

        cartService.addCartItem(addCartItemDTO);
    }

    @RequestMapping(value="/commands/removeCartItem",method = RequestMethod.POST)
    public void removeCartItem(@RequestBody CartItemDTO cartItemDTO){

        cartService.removeCartItem(cartItemDTO);
    }

    /**
     * 结算
     * 将相关的购物车项状态改成待确认状态
     * @param settleDTO
     */
    @RequestMapping(value="/commands/settle",method = RequestMethod.POST)
    public void settle(@RequestBody SettleDTO settleDTO){

        cartService.settle(settleDTO);
    }

    /**
     * 修改购物车项的数量
     * @param changeNumDTO
     */
    @RequestMapping(value="/commands/changeNum",method = RequestMethod.POST)
    public void changeNum(@RequestBody ChangeNumDTO changeNumDTO){

        cartService.changeNum(changeNumDTO);
    }

    /**
     * 确认购物车，创建订单，将相关的购物车项状态设置为已确认
     */
    @RequestMapping(value="/commands/confirm",method = RequestMethod.POST)
    public void confirm(@RequestBody ConfirmDTO confirmDTO){

        cartService.confirm(confirmDTO);
    }
}
