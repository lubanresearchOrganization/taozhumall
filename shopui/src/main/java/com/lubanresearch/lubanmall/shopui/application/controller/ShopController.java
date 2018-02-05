package com.lubanresearch.lubanmall.shopui.application.controller;

import com.lubanmall.merchantserviceapi.bean.ShopDTO;
import com.lubanresearch.lubanmall.shopui.infrastructure.remote.MerchantService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author hilber.cao
 */
@Api(tags = "shop" , description = "店铺")
@Controller
@RequestMapping("/v/0.1/shops")
public class ShopController {

    @Autowired
    private MerchantService merchantService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ShopDTO get(@PathVariable("id") String id) {

        return merchantService.get(id);
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    @ResponseBody
    public ShopDTO addShop(@RequestBody ShopDTO dto){

        return merchantService.addShop(dto);

    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PATCH)
    @ResponseBody
    public ShopDTO updateShop(@PathVariable("id") Long id,@RequestBody ShopDTO dto){

        return merchantService.updateShop(id,dto);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public void removeShop(@PathVariable("id") Long id){


        merchantService.removeShop(id);
    }
}
