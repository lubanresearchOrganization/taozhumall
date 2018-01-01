package com.lubanresearch.lubanmall.orderservice.application.controller;


import com.lubanresearch.lubanmall.common.bean.Response;
import com.lubanresearch.lubanmall.orderservice.domain.CreateDealCommand;
import com.lubanresearch.lubanmall.orderservice.domain.Deal;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * Created by hilbertcao on 2017/12/19.
 */
@Controller
@RequestMapping("/v/0.1/deals")
public class CommandController {

    @Autowired
    private CommandGateway commandGateway;

    @RequestMapping(value = "/d",method = RequestMethod.GET)
    @ResponseBody
    public Response<Deal> addDeal(){

        commandGateway.sendAndWait(
                new CreateDealCommand(BigDecimal.valueOf(11f),1000L));
        return new Response<>(0,"success",null);
    }


}
