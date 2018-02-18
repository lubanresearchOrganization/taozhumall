package com.lubanresearch.lubanmall.customerui.application.controller;

import com.lubanmall.merchantserviceapi.bean.ShopGroupedProductDTO;
import com.lubanresearch.lubanmall.customerui.infrastructure.remote.ShopGroupedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by hilbertcao on 2018/2/18.
 */
@RequestMapping("/v/0.1/shopGroupedProducts")
@Controller
public class ShopGroupedProductQueryController {

    @Autowired
    private ShopGroupedProductService shopGroupedProductService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<ShopGroupedProductDTO> findShopGroupProducts(
            @RequestParam(value = "productIds") List<Long> productIds
    ){

        return shopGroupedProductService.findShopGroupProducts(productIds);
    }
}
