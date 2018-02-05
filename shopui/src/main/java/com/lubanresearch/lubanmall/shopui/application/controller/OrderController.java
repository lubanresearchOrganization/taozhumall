package com.lubanresearch.lubanmall.shopui.application.controller;

import com.lubanmall.orderserviceapi.bean.ChangeOrderTotalDTO;
import com.lubanmall.orderserviceapi.bean.OrderDTO;
import com.lubanresearch.lubanmall.common.bean.Pagination;
import com.lubanresearch.lubanmall.shopui.infrastructure.remote.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hilbertcao on 2018/2/4.
 */
@Controller
@RequestMapping("/v/0.1/orders")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @RequestMapping("/")
    public @ResponseBody
    Pagination<OrderDTO> getOrders(
            @RequestParam("shopId") Long shopId,
            @RequestParam("customerId") Long customerId,
            @RequestParam("status") Long status
    ){

        return orderService.getOrders(shopId, customerId, status);
    }

    @RequestMapping("/{orderId}")
    public @ResponseBody
    OrderDTO getOrder(
            @PathVariable("orderId")Long orderId
    ){

        return orderService.getOrder(orderId);
    }

    @RequestMapping(value = "/{orderId}/commands/deliver", method = RequestMethod.POST)
    @ResponseBody
    public void confirmReceive() {

        orderService.confirmReceive();

    }


    @RequestMapping(value = "/{orderId}/commands/changeTotal", method = RequestMethod.POST)
    @ResponseBody
    public void changeTotal(@RequestBody ChangeOrderTotalDTO changeOrderTotalDTO) {


        orderService.changeTotal(changeOrderTotalDTO);
    }

}
