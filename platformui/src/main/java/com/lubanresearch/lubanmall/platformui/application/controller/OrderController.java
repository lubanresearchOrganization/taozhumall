package com.lubanresearch.lubanmall.platformui.application.controller;

import com.lubanmall.orderserviceapi.bean.OrderDTO;
import com.lubanmall.userserviceapi.bean.UserDTO;
import com.lubanresearch.lubanmall.common.bean.Pagination;
import com.lubanresearch.lubanmall.platformui.remote.OrderService;
import com.lubanresearch.lubanmall.platformui.remote.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/v/0.1/orders")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    Pagination<OrderDTO> getOrders(@RequestParam(value = "dealId") Long dealId,
                                   @RequestParam(value = "shopId") Long shopId,
                                   @RequestParam(value = "customerId") Long customerId,
                                   @RequestParam(value = "status") Byte status,
                                   @RequestParam(value = "page", defaultValue = "0") Integer page,
                                   @RequestParam(value = "size", defaultValue = "10") Integer size) {


        Pagination<OrderDTO> orderDTOPagination = orderService.getOrders(dealId, shopId, customerId, status, page, size);

        List<OrderDTO> orderDTOList = orderDTOPagination.getItems();

        for (OrderDTO orderDTO : orderDTOList) {

            UserDTO userDTO = userService.getUser(orderDTO.getCustomerId());
            orderDTO.setCustomerName(userDTO.getName());

        }

        return orderDTOPagination;

    }

}
