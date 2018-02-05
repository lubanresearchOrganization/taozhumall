package com.lubanresearch.lubanmall.cart.application.controller;

import com.lubanresearch.lubanmall.cartapi.CartDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hilbertcao on 2018/2/4.
 */
@Controller
@RequestMapping("/v/0.1/carts/{customerId}")
public class QueryController {


    @RequestMapping("/")
    public @ResponseBody CartDTO getCustomerCart(@PathVariable("customerId") Long customerId){

        return null;
    }

    @RequestMapping("／toBeConfirmed")
    public @ResponseBody CartDTO getToBeConfirmedCustomerCart(@PathVariable("customerId") Long customerId){

        return null;
    }


}
