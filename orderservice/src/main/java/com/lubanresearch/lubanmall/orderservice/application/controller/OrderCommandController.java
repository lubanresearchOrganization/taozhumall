package com.lubanresearch.lubanmall.orderservice.application.controller;

import com.lubanmall.orderserviceapi.bean.ChangeOrderTotalDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hilbertcao on 2018/2/4.
 */
@Controller
@RequestMapping("/v/0.1/orders/{orderId}/commands/")
public class OrderCommandController {

    @RequestMapping(value = "/confirmReceive", method = RequestMethod.POST)
    @ResponseBody
    public void confirmReceive() {



    }


    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    @ResponseBody
    public void pay() {



    }

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    @ResponseBody
    public void cancel() {



    }

    @RequestMapping(value = "/deliver", method = RequestMethod.POST)
    @ResponseBody
    public void deliver() {



    }

    @RequestMapping(value = "/changeTotal", method = RequestMethod.POST)
    @ResponseBody
    public void changeTotal(@RequestBody ChangeOrderTotalDTO changeOrderTotalDTO) {



    }
}
