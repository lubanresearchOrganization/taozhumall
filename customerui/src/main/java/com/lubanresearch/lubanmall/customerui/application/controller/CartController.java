package com.lubanresearch.lubanmall.customerui.application.controller;

import com.lubanresearch.lubanmall.cartapi.*;
import com.lubanresearch.lubanmall.customerui.infrastructure.remote.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hilbertcao on 2018/2/3.
 */
@Controller
@RequestMapping("/v/0.1/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping("/")
    public @ResponseBody
    List<CartItemDTO> getCustomerCart(@RequestParam(value = "productIds",required = false) List<Long> productIds){

        return cartService.getCustomerCart(1513709082550L,productIds);
    }


    @RequestMapping(value="/commands/addCartItem",method = RequestMethod.POST)
    public @ResponseBody boolean addCartItem(@RequestBody AddCartItemDTO addCartItemDTO){

        return cartService.addCartItem(1513709082550L,addCartItemDTO);
    }

    @RequestMapping(value="/commands/removeCartItem",method = RequestMethod.POST)
    public @ResponseBody boolean removeCartItem(@RequestParam("productId") Long productId){

        return cartService.removeCartItem(1513709082550L,productId);
    }

    /**
     * 结算
     * 将相关的购物车项状态改成待确认状态
     * @param settleDTO
     */
    @RequestMapping(value="/commands/settle",method = RequestMethod.POST)
    public @ResponseBody boolean settle(@RequestBody SettleDTO settleDTO){

        return cartService.settle(1513709082550L,settleDTO);
    }

    /**
     * 修改购物车项的数量
     * @param changeNumDTO
     */
    @RequestMapping(value="/commands/changeNum",method = RequestMethod.POST)
    public @ResponseBody boolean changeNum(@RequestBody ChangeNumDTO changeNumDTO){

        return cartService.changeNum(1513709082550L,changeNumDTO);
    }

    /**
     * 确认购物车，创建订单，将相关的购物车项状态设置为已确认
     */
    @RequestMapping(value="/commands/confirm",method = RequestMethod.POST)
    public @ResponseBody boolean confirm(@RequestBody ConfirmDTO confirmDTO){

        return cartService.confirm(1513709082550L,confirmDTO);
    }
}
