package com.lubanresearch.lubanmall.shopui.application.controller;

import com.lubanmall.orderserviceapi.bean.ChangeOrderTotalDTO;
import com.lubanmall.orderserviceapi.bean.OrderDTO;
import com.lubanresearch.lubanmall.common.bean.Pagination;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hilbertcao on 2018/2/4.
 */
@Controller
@RequestMapping("/v/0.1/orders")
public class OrderController {


    @RequestMapping("/")
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

    @RequestMapping(value = "/{orderId}/commands/deliver", method = RequestMethod.POST)
    @ResponseBody
    public void confirmReceive() {



    }


    @RequestMapping(value = "/{orderId}/commands/changeTotal", method = RequestMethod.POST)
    @ResponseBody
    public void changeTotal(@RequestBody ChangeOrderTotalDTO changeOrderTotalDTO) {



    }

}
