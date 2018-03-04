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
    public Boolean confirmReceive(@PathVariable("orderId")Long orderId) {


        commandGateway.sendAndWait(new ConfirmReceiveCommand(orderId));
        return true;
    }


    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    @ResponseBody
    public Boolean pay(@PathVariable("orderId")Long orderId) {

        commandGateway.sendAndWait(new PayCommand(orderId));
        return true;
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    @ResponseBody
    public Boolean cancel(@PathVariable("orderId")Long orderId) {

        commandGateway.sendAndWait(new CancelCommand(orderId));
        return true;
    }

    @RequestMapping(value = "/deliver", method = RequestMethod.POST)
    @ResponseBody
    public Boolean deliver(@PathVariable("orderId")Long orderId) {


        commandGateway.sendAndWait(new DeliverCommand(orderId));
        return true;
    }

    @RequestMapping(value = "/changeTotal", method = RequestMethod.POST)
    @ResponseBody
    public Boolean changeTotal(@PathVariable("orderId")Long orderId,@RequestBody ChangeOrderTotalDTO changeOrderTotalDTO) {


        commandGateway.sendAndWait(new ChangeTotalCommand(orderId,changeOrderTotalDTO.getTotal()));
        return true;
    }
}
