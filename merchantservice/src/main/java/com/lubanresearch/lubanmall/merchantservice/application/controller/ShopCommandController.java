package com.lubanresearch.lubanmall.merchantservice.application.controller;

import com.lubanmall.merchantserviceapi.bean.ShopDTO;
import com.lubanresearch.lubanmall.common.bean.Response;
import com.lubanresearch.lubanmall.merchantservice.domain.Shop;
import com.lubanresearch.lubanmall.merchantservice.domain.command.AddShopCommand;
import com.lubanresearch.lubanmall.merchantservice.domain.command.RemoveShopCommand;
import com.lubanresearch.lubanmall.merchantservice.domain.command.UpdateShopCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hilbertcao on 2017/12/19.
 */
@Controller
@RequestMapping("/v/0.1/shops")
public class ShopCommandController {

    @Autowired
    private CommandGateway commandGateway;

    @RequestMapping(value = "/",method = RequestMethod.POST)
    @ResponseBody
    public Response<Shop> addShop(@RequestBody ShopDTO dto){

        Shop shop = commandGateway.sendAndWait(new AddShopCommand(dto.getName(),dto.getAccountId(),dto.getImgUrl(),dto.getDiscription()));
        return new Response<>(0,"success",shop);
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.PATCH)
    @ResponseBody
    public Response<Shop> updateShop(@PathVariable("id") Long id,@RequestBody ShopDTO dto){

        Shop shop = commandGateway.sendAndWait(new UpdateShopCommand(dto.getId(),dto.getName(),dto.getImgUrl(),dto.getDiscription()));
        return new Response<>(0,"success",shop);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public Response removeShop(@PathVariable("id") Long id){

        commandGateway.sendAndWait(new RemoveShopCommand(id));
        return new Response<>(0,"success",null);
    }
}
