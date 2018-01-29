package com.lubanresearch.lubanmall.customerui.application.controller;

import com.lubanmall.catagoryserviceapi.bean.CategoryDTO;
import com.lubanresearch.lubanmall.customerui.infrastructure.remote.CategoryService;
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
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<CategoryDTO> getCategorys(@RequestParam(value = "parentId",required = false) Long parentId) {

        return categoryService.getCategorys(parentId,false);

    }

}
