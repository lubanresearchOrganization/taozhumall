package com.lubanresearch.lubanmall.orderservice.application.controller;

import com.lubanmall.orderserviceapi.bean.OrderDTO;
import com.lubanresearch.lubanmall.common.bean.Pagination;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hilbertcao on 2018/2/4.
 */
@Controller
@RequestMapping("/v/0.1")
public class QueryController {

    @RequestMapping("/orders/")
    public @ResponseBody Pagination<OrderDTO> getOrders(
            @RequestParam("shopId") Long shopId,
            @RequestParam("customerId") Long customerId,
            @RequestParam("status") Long status
            ){

        return null;
    }


    @RequestMapping("/orders/{orderId}")
    public @ResponseBody OrderDTO getOrder(
            @RequestParam("orderId") Long orderId
    ){

        return null;
    }
}
