package com.lubanresearch.lubanmall.customerui.application.controller;

import com.lubanmall.categoryserviceapi.bean.CategoryDTO;
import com.lubanresearch.lubanmall.customerui.infrastructure.remote.CategoryService;
import com.lubanresearch.lubanmall.customerui.infrastructure.remote.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * Created by hilbertcao on 2018/1/22.
 */
@Controller
@RequestMapping("/v/0.1/categorys")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private MerchantService merchantService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<CategoryDTO> getCategorys(@RequestParam(value = "parentId",required = false) Long parentId,
                                          @RequestParam(value = "shopId",required = false) Long shopId) {

        if(shopId!=null){
            return merchantService.getCategorys(parentId,shopId,false);
        }
        return categoryService.getCategorys(parentId,false);

    }

}
