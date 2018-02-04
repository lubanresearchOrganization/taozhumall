package com.lubanresearch.lubanmall.customerui.application.controller;

import com.lubanmall.orderserviceapi.bean.OrderDTO;
import com.lubanresearch.lubanmall.common.bean.Pagination;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hilbertcao on 2018/2/4.
 */
@Controller
@RequestMapping("/v/0.1")
public class OrderController {


    @RequestMapping("/orders/")
    public @ResponseBody
    Pagination<OrderDTO> getOrders(
            @RequestParam("shopId") Long shopId,
            @RequestParam("customerId") Long customerId,
            @RequestParam("status") Long status
    ){

        return null;
    }

    @RequestMapping("/{orderId}")
    public @ResponseBody
    OrderDTO getOrder(
            @PathVariable("orderId")Long orderId
    ){

        return null;
    }

    @RequestMapping(value = "/orders/{orderId}/commands/confirmReceive", method = RequestMethod.POST)
    @ResponseBody
    public void confirmReceive() {



    }


    @RequestMapping(value = "/orders/{orderId}/commands/pay", method = RequestMethod.POST)
    @ResponseBody
    public void pay() {



    }

    @RequestMapping(value = "/orders/{orderId}/commands/cancel", method = RequestMethod.POST)
    @ResponseBody
    public void cancel() {



    }

    @RequestMapping(value = "/orders/{orderId}/commands/deliver", method = RequestMethod.POST)
    @ResponseBody
    public void deliver() {



    }

}
