package com.lubanresearch.lubanmall.orderservice.application.controller;

import com.lubanmall.orderserviceapi.bean.ChangeOrderTotalDTO;
import com.lubanresearch.lubanmall.orderservice.domain.command.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hilbertcao on 2018/2/4.
 */
@Controller
@RequestMapping("/v/0.1/orders/{orderId}/commands/")
public class OrderCommandController {
    @Autowired
    private CommandGateway commandGateway;

    @RequestMapping(value = "/confirmReceive", method = RequestMethod.POST)
    @ResponseBody
    public void confirmReceive(@PathVariable("orderId")Long orderId) {


        commandGateway.sendAndWait(new ConfirmReceiveCommand(orderId));

    }


    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    @ResponseBody
    public void pay(@PathVariable("orderId")Long orderId) {

        commandGateway.sendAndWait(new PayCommand(orderId));

    }

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    @ResponseBody
    public void cancel(@PathVariable("orderId")Long orderId) {

        commandGateway.sendAndWait(new CancelCommand(orderId));

    }

    @RequestMapping(value = "/deliver", method = RequestMethod.POST)
    @ResponseBody
    public void deliver(@PathVariable("orderId")Long orderId) {


        commandGateway.sendAndWait(new DeliverCommand(orderId));
    }

    @RequestMapping(value = "/changeTotal", method = RequestMethod.POST)
    @ResponseBody
    public void changeTotal(@PathVariable("orderId")Long orderId,@RequestBody ChangeOrderTotalDTO changeOrderTotalDTO) {


        commandGateway.sendAndWait(new ChangeTotalCommand(orderId,changeOrderTotalDTO.getTotal()));
    }
}
