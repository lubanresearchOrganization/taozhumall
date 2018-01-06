package com.lubanresearch.lubanmall.orderservice.application.controller;


import com.lubanmall.orderserviceapi.bean.DealDTO;
import com.lubanmall.orderserviceapi.bean.OrderDTO;
import com.lubanmall.orderserviceapi.bean.OrderItemDTO;
import com.lubanresearch.lubanmall.common.bean.Response;
import com.lubanresearch.lubanmall.orderservice.domain.CreateDealCommand;
import com.lubanresearch.lubanmall.orderservice.domain.Order;
import com.lubanresearch.lubanmall.orderservice.domain.OrderItem;
import com.lubanresearch.lubanmall.orderservice.domain.Product;
import com.lubanresearch.lubanmall.orderservice.infrastructure.remote.MerchantService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hilbertcao on 2017/12/19.
 */
@Controller
@RequestMapping("/v/0.1/deals")
public class CommandController {


    @Autowired
    private MerchantService merchantService;

    @Autowired
    private CommandGateway commandGateway;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Response<Product> addDeal(@RequestBody DealDTO deal) {

        List<OrderDTO> orderDTOList = deal.getOrderList();


        List<Order> orderList = new ArrayList<>();

        for (OrderDTO orderDTO : orderDTOList) {


            List<OrderItemDTO> orderItemDTOList = orderDTO.getOrderItemList();

            List<OrderItem> orderItemList = new ArrayList<>();

            for (OrderItemDTO orderItemDTO : orderItemDTOList) {

                Product product = merchantService.getProduct(orderItemDTO.getProductId());



                OrderItem orderItem = new OrderItem(orderItemDTO.getProductId(), product.getUnitPrice(), orderItemDTO.getProductNum());

                orderItemList.add(orderItem);

            }

            Order order = new Order(orderDTO.getCustomerId(), orderDTO.getRemark(), orderDTO.getShopId(), orderItemList);
            orderList.add(order);

        }

        Long id = commandGateway.sendAndWait(
                new CreateDealCommand(deal.getCustomerId(), orderList));

        return new Response<Product>(0, "success", null);
    }


}
