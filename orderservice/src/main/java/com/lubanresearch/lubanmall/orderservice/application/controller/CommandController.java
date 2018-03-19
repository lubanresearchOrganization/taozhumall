package com.lubanresearch.lubanmall.orderservice.application.controller;


import com.lubanmall.merchantserviceapi.bean.ProductDTO;
import com.lubanmall.orderserviceapi.bean.CreateDealDTO;
import com.lubanmall.orderserviceapi.bean.ProductItemDTO;
import com.lubanmall.orderserviceapi.bean.UpdateDealStatusDTO;
import com.lubanmall.orderserviceapi.bean.UpdateOrderStatusDTO;
import com.lubanresearch.lubanmall.orderservice.domain.Order;
import com.lubanresearch.lubanmall.orderservice.domain.OrderItem;
import com.lubanresearch.lubanmall.orderservice.domain.command.CreateDealCommand;
import com.lubanresearch.lubanmall.orderservice.domain.command.DeteleDealCommand;
import com.lubanresearch.lubanmall.orderservice.domain.command.UpdateDealStatusCommand;
import com.lubanresearch.lubanmall.orderservice.domain.command.UpdateOrderStatusCommand;
import com.lubanresearch.lubanmall.orderservice.infrastructure.remote.MerchantService;
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
    public synchronized boolean addDeal(@RequestBody CreateDealDTO createDealDTO) {


        List<ProductDTO> productDTOs = createDealDTO.getItems().stream().map(item->{
            return merchantService.getProduct(item.getProductId());
        }).collect(Collectors.toList());
        Map<Long,ProductItemDTO> longProductItemMap= createDealDTO.getItems().stream().collect(Collectors.toMap(ProductItemDTO::getProductId, Function.identity()));
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
                                orderItem.setProductNum(longProductItemMap.get(productDTO.getId()).getProductNum());
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
