package com.lubanresearch.lubanmall.shopui.application.controller;

import com.lubanmall.merchantserviceapi.bean.ShopDTO;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author hilber.cao
 */
@Api(tags = "shop" , description = "店铺")
@Controller
@RequestMapping("/v/0.1/shops")
public class ShopController {
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ShopDTO get(@PathVariable("id") String id) {

        return null;
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    @ResponseBody
    public String addShop(@RequestBody ShopDTO dto){
        dto.getName();


        return "success";

    }

}
