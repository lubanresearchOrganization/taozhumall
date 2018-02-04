package com.lubanresearch.lubanmall.merchantservice.application.controller;

import com.lubanmall.catagoryserviceapi.bean.CategoryDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by hilbertcao on 2018/2/4.
 */
@Controller
@RequestMapping("/v/0.1/categorys")
public class CategoryQueryController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<CategoryDTO> getCategorys(@RequestParam(value = "parentId",required = false) Long parentId,
                                          @RequestParam(value = "shopId",required = false) Long shopId
    ) {

        return null;

    }
}
