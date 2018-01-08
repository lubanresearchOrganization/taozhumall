package com.lubanresearch.lubanmall.orderservice.application.controller;


import com.lubanmall.orderserviceapi.bean.DealDTO;
import com.lubanmall.orderserviceapi.bean.OrderDTO;
import com.lubanmall.orderserviceapi.bean.OrderItemDTO;
import com.lubanresearch.lubanmall.orderservice.domain.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hilbertcao on 2017/12/19.
 */
@Controller
@RequestMapping("/v/0.1/deals")
public class CommandController {

    @Autowired
    private CommandGateway commandGateway;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Long addDeal(@RequestBody DealDTO deal) {

        List<OrderDTO> orderDTOList = deal.getOrderList();


        List<Order> orderList = new ArrayList<>();

        for (OrderDTO orderDTO : orderDTOList) {


            List<OrderItemDTO> orderItemDTOList = orderDTO.getOrderItemList();

            List<OrderItem> orderItemList = new ArrayList<>();

            for (OrderItemDTO orderItemDTO : orderItemDTOList) {


                OrderItem orderItem = new OrderItem(orderItemDTO.getProductId(), orderItemDTO.getProductNum());

                orderItemList.add(orderItem);

            }

            Order order = new Order(orderDTO.getCustomerId(), orderDTO.getRemark(), orderDTO.getShopId(), orderItemList);
            orderList.add(order);

        }

        Long id = commandGateway.sendAndWait(
                new CreateDealCommand(deal.getCustomerId(), orderList));

        return id;
    }



    @RequestMapping(value = "/{dealId}/b", method = RequestMethod.GET)
    @ResponseBody
    public Object b(@PathVariable("dealId")Long dealId,@RequestParam("total")BigDecimal total) {



        Object id = commandGateway.sendAndWait(
                new UpdateDealTotalCommand(dealId,total));

        return id;
    }


}
