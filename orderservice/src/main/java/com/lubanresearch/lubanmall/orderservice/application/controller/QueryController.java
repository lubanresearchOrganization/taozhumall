package com.lubanresearch.lubanmall.orderservice.application.controller;

import com.lubanmall.orderserviceapi.bean.OrderDTO;
import com.lubanmall.orderserviceapi.bean.OrderItemDTO;
import com.lubanresearch.lubanmall.common.bean.Pagination;

import com.lubanresearch.lubanmall.orderservice.domain.bean.Order;
import com.lubanresearch.lubanmall.orderservice.domain.bean.OrderItem;

import com.lubanresearch.lubanmall.orderservice.domain.query.condition.OrderItemQueryCondition;
import com.lubanresearch.lubanmall.orderservice.domain.query.condition.OrderQueryCondition;

import com.lubanresearch.lubanmall.orderservice.infrastructure.persistence.db.OrderItemMapper;
import com.lubanresearch.lubanmall.orderservice.infrastructure.persistence.db.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hilbertcao on 2018/2/4.
 */
@Controller
@RequestMapping("/v/0.1")
public class QueryController {


    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderItemMapper orderItemMapper;


    @RequestMapping("/orders/")
    public @ResponseBody
    Pagination<OrderDTO> getOrders(
            @RequestParam(value = "dealId") Long dealId,
            @RequestParam(value = "shopId") Long shopId,
            @RequestParam(value = "customerId") Long customerId,
            @RequestParam(value = "status") Byte status,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {


        OrderQueryCondition.Criteria orderCriteria = new OrderQueryCondition().createCriteria();

        orderCriteria.andShopIdEqualTo(shopId)
                .andDealIdEqualTo(dealId)
                .andCustomerIdEqualTo(customerId)
                .andStatusEqualTo(status);

        List<Order> orderList = orderMapper.selectByExample(
                orderCriteria.example().page(page, size).orderBy("create_time desc"));


        List<OrderDTO> orderDTOList = new ArrayList<>();

        for (Order order : orderList) {

            OrderDTO orderDTO = new OrderDTO();

            orderDTO.setId(order.getId());
            orderDTO.setShopId(order.getShopId());
            orderDTO.setRemark(order.getRemark());
            orderDTO.setCustomerId(order.getCustomerId());
            orderDTO.setTotalAmount(order.getTotalAmount());
            orderDTO.setCreateTime(order.getCreateTime());
            orderDTO.setStatus(order.getStatus());
            OrderItemQueryCondition.Criteria criteria = new OrderItemQueryCondition().createCriteria();

            criteria.andOrderIdEqualTo(order.getId());

            List<OrderItem> orderItemList = orderItemMapper.selectByExample(
                    criteria.example());

            List<OrderItemDTO> orderItemDTOList = new ArrayList<>();

            for (OrderItem orderItem : orderItemList) {


                OrderItemDTO orderItemDTO = new OrderItemDTO();
                orderItemDTO.setProductNum(orderItem.getProductNum());
                orderItemDTO.setProductId(orderItem.getProductId());
                orderItemDTO.setUnitPrice(orderItem.getUnitPrice());
                orderItemDTOList.add(orderItemDTO);

            }
            orderDTO.setOrderItemList(orderItemDTOList);

            orderDTOList.add(orderDTO);

        }

        Long total = orderMapper.countByExample(orderCriteria.example());

        Pagination<OrderDTO> pagination = new Pagination<>();
        pagination.setItems(orderDTOList);
        pagination.setTotal(total.intValue());
        pagination.setPageCount((pagination.getTotal() % size == 0) ? (pagination.getTotal() / size) : (pagination.getTotal() / size + 1));
        pagination.setPageIndex(page);

        return pagination;


    }


    @RequestMapping("/orders/{orderId}")
    public @ResponseBody
    OrderDTO getOrder(
            @PathVariable("orderId") Long orderId
    ) {

        Order order = orderMapper.selectByPrimaryKey(orderId);

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId(order.getId());
        orderDTO.setCustomerId(order.getCustomerId());

        orderDTO.setRemark(order.getRemark());
        orderDTO.setShopId(order.getShopId());
        orderDTO.setTotalAmount(order.getTotalAmount());


        OrderItemQueryCondition.Criteria criteria = new OrderItemQueryCondition().createCriteria();

        criteria.andOrderIdEqualTo(order.getId());

        List<OrderItem> orderItemList = orderItemMapper.selectByExample(
                criteria.example());


        List<OrderItemDTO> orderItemDTOList = new ArrayList<>();

        for (OrderItem orderItem : orderItemList) {


            OrderItemDTO orderItemDTO = new OrderItemDTO();
            orderItemDTO.setProductNum(orderItem.getProductNum());
            orderItemDTO.setProductId(orderItem.getProductId());
            orderItemDTO.setUnitPrice(orderItem.getUnitPrice());

            orderItemDTOList.add(orderItemDTO);

        }
        orderDTO.setOrderItemList(orderItemDTOList);

        return orderDTO;
    }
}
