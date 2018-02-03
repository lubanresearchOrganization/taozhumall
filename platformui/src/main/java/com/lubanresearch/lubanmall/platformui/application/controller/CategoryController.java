package com.lubanresearch.lubanmall.platformui.application.controller;

import com.lubanmall.catagoryserviceapi.bean.CategoryDTO;
import com.lubanresearch.lubanmall.platformui.remote.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/v/0.1/categorys")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<CategoryDTO> getCategorys(@RequestParam(value = "parentId",required = false) Long parentId) {



        return categoryService.getCategorys(parentId);

    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public CategoryDTO addCategory(@RequestBody CategoryDTO category) {


        return categoryService.addCategory(category);

    }


}
