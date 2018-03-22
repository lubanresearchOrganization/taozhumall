package com.lubanresearch.lubanmall.customerui.application.controller;

import com.lubanresearch.lubanmall.cartapi.*;
import com.lubanresearch.lubanmall.common.exception.UIException;
import com.lubanresearch.lubanmall.customerui.infrastructure.remote.CartService;
import com.lubanresearch.lubanmall.ssoclient.bean.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    List<CartItemDTO> getCustomerCart(@RequestParam(value = "productIds",required = false) List<Long> productIds
    ,HttpSession session
    ){

        return cartService.getCustomerCart(getCustomerId(session),productIds);
    }

    private Long getCustomerId(HttpSession session){
        Authentication authentication = (Authentication) session.getAttribute("authentication");
        if(authentication==null){

            throw new UIException(500,"用户未登录");
        }
        return authentication.getUserId();
    }

    @RequestMapping(value="/commands/addCartItem",method = RequestMethod.POST)
    public @ResponseBody boolean addCartItem(@RequestBody AddCartItemDTO addCartItemDTO,HttpSession session){

        return cartService.addCartItem(getCustomerId(session),addCartItemDTO);
    }

    @RequestMapping(value="/commands/removeCartItem",method = RequestMethod.POST)
    public @ResponseBody boolean removeCartItem(@RequestParam("productId") Long productId,HttpSession session){

        return cartService.removeCartItem(getCustomerId(session),productId);
    }

    /**
     * 结算
     * 将相关的购物车项状态改成待确认状态
     * @param settleDTO
     */
    @RequestMapping(value="/commands/settle",method = RequestMethod.POST)
    public @ResponseBody boolean settle(@RequestBody SettleDTO settleDTO,HttpSession session){

        return cartService.settle(getCustomerId(session),settleDTO);
    }

    /**
     * 修改购物车项的数量
     * @param changeNumDTO
     */
    @RequestMapping(value="/commands/changeNum",method = RequestMethod.POST)
    public @ResponseBody boolean changeNum(@RequestBody ChangeNumDTO changeNumDTO,HttpSession session){

        return cartService.changeNum(getCustomerId(session),changeNumDTO);
    }

    /**
     * 确认购物车，创建订单，将相关的购物车项状态设置为已确认
     */
    @RequestMapping(value="/commands/confirm",method = RequestMethod.POST)
    public @ResponseBody boolean confirm(@RequestBody ConfirmDTO confirmDTO,HttpSession session){

        return cartService.confirm(getCustomerId(session),confirmDTO);
    }
}
