package com.lubanresearch.lubanmall.customerui.application.controller;

import com.lubanmall.orderserviceapi.bean.CreateDealDTO;
import com.lubanmall.orderserviceapi.bean.OrderDTO;
import com.lubanresearch.lubanmall.common.bean.Pagination;
import com.lubanresearch.lubanmall.customerui.infrastructure.remote.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public boolean addDeal(@RequestBody CreateDealDTO createDealDTO) {

        createDealDTO.setCustomerId(1513709082550L);
        return orderService.createDeal(createDealDTO);
    }

    @RequestMapping("/orders/")
    public @ResponseBody
    Pagination<OrderDTO> getOrders(
            @RequestParam(value = "id",required = false) Long id,
            @RequestParam(value = "shopId",required = false) Long shopId,
            @RequestParam(value = "status",required = false) Long status,
            @RequestParam(value = "page", defaultValue = "0",required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "10",required = false) Integer size
    ){

        return orderService.getOrders(id,shopId, 1513709082550L, status,page,size);
    }

    @RequestMapping("/{orderId}")
    public @ResponseBody
    OrderDTO getOrder(
            @PathVariable("orderId")Long orderId
    ){

        return orderService.getOrder(orderId);
    }

    @RequestMapping(value = "/orders/{orderId}/commands/confirmReceive", method = RequestMethod.POST)
    @ResponseBody
    public void confirmReceive() {

        orderService.confirmReceive();

    }


    @RequestMapping(value = "/orders/{orderId}/commands/pay", method = RequestMethod.POST)
    @ResponseBody
    public void pay() {

        orderService.pay();

    }

    @RequestMapping(value = "/orders/{orderId}/commands/cancel", method = RequestMethod.POST)
    @ResponseBody
    public void cancel() {

        orderService.cancel();

    }

    @RequestMapping(value = "/orders/{orderId}/commands/deliver", method = RequestMethod.POST)
    @ResponseBody
    public void deliver() {

        orderService.deliver();

    }


}
