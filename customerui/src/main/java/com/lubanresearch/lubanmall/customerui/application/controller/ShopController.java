package com.lubanresearch.lubanmall.customerui.application.controller;

import com.lubanmall.merchantserviceapi.bean.ShopDTO;
import com.lubanresearch.lubanmall.common.bean.Pagination;
import com.lubanresearch.lubanmall.customerui.infrastructure.remote.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hilbertcao on 2018/1/30.
 */
@Controller
@RequestMapping("/v/0.1/shops")
public class ShopController {

    @Autowired
    private SearchService searchService;
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    Pagination<ShopDTO> findShops(
            @RequestParam(value = "key",required = false) String  key,
            @RequestParam(value = "page", defaultValue = "0",required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "10",required = false) Integer size
    ){

        return searchService.findShops(key, page, size);
    }
}
