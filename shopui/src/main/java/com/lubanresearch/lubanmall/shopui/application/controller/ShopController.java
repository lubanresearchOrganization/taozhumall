package com.lubanresearch.lubanmall.shopui.application.controller;

import com.lubanmall.merchantserviceapi.bean.ShopDTO;
import com.lubanresearch.lubanmall.common.bean.Response;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
