package com.lubanresearch.lubanmall.customerui.application.controller;

import com.lubanmall.orderserviceapi.bean.CreateDealDTO;
import com.lubanmall.orderserviceapi.bean.OrderDTO;
import com.lubanresearch.lubanmall.common.bean.Pagination;
import com.lubanresearch.lubanmall.common.exception.UIException;
import com.lubanresearch.lubanmall.customerui.infrastructure.remote.OrderService;
import com.lubanresearch.lubanmall.ssoclient.bean.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Created by hilbertcao on 2018/2/4.
 */
@Controller
@RequestMapping("/v/0.1")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/deals/", method = RequestMethod.POST)
    @ResponseBody
    public boolean addDeal(@RequestBody CreateDealDTO createDealDTO,HttpSession session) {

        createDealDTO.setCustomerId(getCustomerId(session));
        return orderService.createDeal(createDealDTO);
    }

    private Long getCustomerId(HttpSession session){
        return Optional.ofNullable((Authentication) session.getAttribute("authentication"))
                .orElseThrow(() -> {
                    return new UIException(500, "用户未登录");
                })
                .getUserId();
    }

    @RequestMapping("/orders/")
    public @ResponseBody
    Pagination<OrderDTO> getOrders(
            @RequestParam(value = "id",required = false) Long id,
            @RequestParam(value = "shopId",required = false) Long shopId,
            @RequestParam(value = "status",required = false) Long status,
            @RequestParam(value = "page", defaultValue = "0",required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "10",required = false) Integer size,
            HttpSession session
    ){

        return orderService.getOrders(id,shopId, getCustomerId(session), status,page,size);
    }

    @RequestMapping("/orders/{orderId}")
    public @ResponseBody
    OrderDTO getOrder(
            @PathVariable("orderId")Long orderId
    ){

        return orderService.getOrder(orderId);
    }

    @RequestMapping(value = "/orders/{orderId}/commands/confirmReceive", method = RequestMethod.POST)
    @ResponseBody
    public Boolean confirmReceive(@PathVariable("orderId")Long orderId) {

        return orderService.confirmReceive(orderId);

    }


    @RequestMapping(value = "/orders/{orderId}/commands/pay", method = RequestMethod.POST)
    @ResponseBody
    public Boolean pay(@PathVariable("orderId")Long orderId) {

        return orderService.pay(orderId);

    }

    @RequestMapping(value = "/orders/{orderId}/commands/cancel", method = RequestMethod.POST)
    @ResponseBody
    public Boolean cancel(@PathVariable("orderId")Long orderId) {

        return orderService.cancel(orderId);

    }

    @RequestMapping(value = "/orders/{orderId}/commands/deliver", method = RequestMethod.POST)
    @ResponseBody
    public Boolean deliver(@PathVariable("orderId")Long orderId) {

        return orderService.deliver(orderId);

    }


}
