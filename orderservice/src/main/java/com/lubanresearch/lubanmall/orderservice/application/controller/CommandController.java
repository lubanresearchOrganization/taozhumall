package com.lubanresearch.lubanmall.orderservice.application.controller;


import com.lubanmall.merchantserviceapi.bean.ProductDTO;
import com.lubanmall.orderserviceapi.bean.*;
import com.lubanresearch.lubanmall.orderservice.domain.*;
import com.lubanresearch.lubanmall.orderservice.domain.command.CreateDealCommand;
import com.lubanresearch.lubanmall.orderservice.domain.command.DeteleDealCommand;
import com.lubanresearch.lubanmall.orderservice.domain.command.UpdateDealStatusCommand;
import com.lubanresearch.lubanmall.orderservice.domain.command.UpdateOrderStatusCommand;
import com.lubanresearch.lubanmall.orderservice.infrastructure.remote.MerchantService;
import org.apache.commons.collections.map.HashedMap;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by hilbertcao on 2017/12/19.
 */
@Controller
@RequestMapping("/v/0.1/deals")
public class CommandController {

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    MerchantService merchantService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public boolean addDeal(@RequestBody CreateDealDTO createDealDTO) {


        List<ProductDTO> productDTOs = createDealDTO.getItems().stream().map(item->{
            return merchantService.getProduct(item.getProductId());
        }).collect(Collectors.toList());
        Map<Long,OrderItemDTO> orderItemMap= createDealDTO.getItems().stream().collect(Collectors.toMap(OrderItemDTO::getProductId, Function.identity()));
        Map<Long,List<ProductDTO>> shopProductMap = productDTOs.stream().collect(Collectors.groupingBy(ProductDTO::getShopId));

        List<Order> orderList = new ArrayList<>();
        for(Long shopId:shopProductMap.keySet()){

            Order order = new Order(
                    createDealDTO.getCustomerId(),
                    Optional.ofNullable(createDealDTO.getRemarkMap()).orElse(new HashMap<Long,String>()).get(shopId), shopId,
                    shopProductMap.get(shopId).stream().map(
                            productDTO ->{

                                OrderItem orderItem = new OrderItem();
                                orderItem.setId(System.nanoTime());
                                orderItem.setProductId(productDTO.getId());
                                orderItem.setUnitPrice(productDTO.getUnitPrice());
                                orderItem.setProductNum(orderItemMap.get(productDTO.getId()).getProductNum());
                                return orderItem;
                            }
                    ).collect(Collectors.toList())
            );
            orderList.add(order);
        }


        commandGateway.sendAndWait(
                new CreateDealCommand(createDealDTO.getCustomerId(), orderList));
        return true;

    }


    @RequestMapping(value = "/updateDealStatus", method = RequestMethod.PATCH)
    @ResponseBody
    public String updateDealStatus(@RequestBody UpdateDealStatusDTO updateDealStatusDTO) {

        Object id = commandGateway.sendAndWait(
                new UpdateDealStatusCommand(updateDealStatusDTO.getId(), updateDealStatusDTO.getStatus()));

        return "success";

    }


    @RequestMapping(value = "/updateOrderStatus", method = RequestMethod.PATCH)
    @ResponseBody
    public String updateDealStatus(@RequestBody UpdateOrderStatusDTO updateOrderStatusDTO) {

        Object id = commandGateway.sendAndWait(
                new UpdateOrderStatusCommand(updateOrderStatusDTO.getId(), updateOrderStatusDTO.getOrderId(), updateOrderStatusDTO.getOrderStatus()));

        return "success";

    }


    @RequestMapping(value = "/{dealId}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("dealId") Long dealId) {


        Object id = commandGateway.sendAndWait(new DeteleDealCommand(dealId));
        return "success";
    }


}
