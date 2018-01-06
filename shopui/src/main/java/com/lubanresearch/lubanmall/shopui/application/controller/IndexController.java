package com.lubanresearch.lubanmall.shopui.application.controller;

import com.lubanmall.merchantserviceapi.bean.ShopDTO;
import com.lubanresearch.lubanmall.shopui.infrastructure.remote.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author hilbert.cao
 */
@ApiIgnore
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private MerchantService merchantService;
    @RequestMapping(path = {"/go","/"})
    @ResponseBody
    public String go() {
        return "success!";
    }


    @RequestMapping(path = {"/b"})
    @ResponseBody
    public ShopDTO b() {


        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setName("a");
        shopDTO.setAccountId(12L);
        ShopDTO result= null;
        return merchantService.addShop(shopDTO);
    }
}
