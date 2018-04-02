package com.lubanresearch.lubanmall.shopui.application.controller;

import com.lubanmall.orderserviceapi.bean.ChangeOrderTotalDTO;
import com.lubanmall.orderserviceapi.bean.OrderDTO;
import com.lubanresearch.lubanmall.common.bean.Pagination;
import com.lubanresearch.lubanmall.shopui.infrastructure.remote.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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
            @RequestParam(value = "id",required = false) Long id,
            @RequestParam(value = "customerId",required = false) Long customerId,
            @RequestParam(value = "status",required = false) Long status,
            @RequestParam(value = "page", defaultValue = "0",required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "10",required = false) Integer size,
            HttpSession session
    ){

        Long shopId = 1513709082550L;
        return orderService.getOrders(id,shopId, customerId, status,page,size);
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
    public Boolean deliver(@PathVariable("orderId")Long orderId) {

        return orderService.deliver(orderId);

    }


    @RequestMapping(value = "/{orderId}/commands/changeTotal", method = RequestMethod.POST)
    @ResponseBody
    public Boolean changeTotal(@PathVariable("orderId")Long orderId,@RequestBody ChangeOrderTotalDTO changeOrderTotalDTO) {


        return orderService.changeTotal(orderId,changeOrderTotalDTO);
    }

}
