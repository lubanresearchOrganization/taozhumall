package com.lubanresearch.lubanmall.cart.application.controller;

import com.lubanresearch.lubanmall.cartapi.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by hilbertcao on 2018/2/4.
 */
@Controller
@RequestMapping("/v/0.1/carts／{customerId}")
public class CommandController {


    @RequestMapping(value="/commands/addCartItem",method = RequestMethod.POST)
    public void addCartItem(@RequestBody AddCartItemDTO addCartItemDTO){

    }

    @RequestMapping(value="/commands/removeCartItem",method = RequestMethod.POST)
    public void removeCartItem(@RequestBody CartItemDTO cartItemDTO){

    }

    /**
     * 结算
     * 将相关的购物车项状态改成待确认状态
     * @param settleDTO
     */
    @RequestMapping(value="/commands/settle",method = RequestMethod.POST)
    public void settle(@RequestBody SettleDTO settleDTO){

    }

    /**
     * 修改购物车项的数量
     * @param changeNumDTO
     */
    @RequestMapping(value="/commands/changeNum",method = RequestMethod.POST)
    public void changeNum(@RequestBody ChangeNumDTO changeNumDTO){

    }

    /**
     * 确认购物车，创建订单，将相关的购物车项状态设置为已确认
     */
    @RequestMapping(value="/commands/confirm",method = RequestMethod.POST)
    public void confirm(@RequestBody ConfirmDTO confirmDTO){

    }
}
