package com.lubanresearch.lubanmall.catagoryservice.application.controller;


import com.lubanresearch.lubanmall.catagoryservice.domain.Category;
import com.lubanresearch.lubanmall.catagoryservice.service.CategoryService;
import com.lubanresearch.lubanmall.common.bean.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(tags = "category", description = "类目控制器")
@Controller
@RequestMapping("/categorys")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @ApiOperation(produces = "application/json", value = "添加类目")
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Response<Category> insert(@RequestParam(value = "name") String name, @RequestParam(value = "parent_id") Long parent_id) {


        Category category = new Category();
        category.setId(new Date().getTime());
        category.setName(name);
        category.setParentId(parent_id);
        category.setCreateTime(new Date());

        boolean isSuccess = categoryService.addCatagory(category);

        if (isSuccess) {

            return new Response(0, "成功", "");

        } else {

            return new Response(1, "失败", "");

        }


    }

    @ApiOperation(produces = "application/json", value = "通过父类目id获得类目")
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public Response<Category> getTopCategory(@RequestParam(value = "parent_id") Long parent_id) {


            List<Category> categoryList = categoryService.getCatagoryByParentId(parent_id);

            return new Response(0, "成功", categoryList);

    }


    @ApiOperation(produces = "application/json", value = "通过id获得子类目")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Response<Category> getSubCategoryById(@PathVariable("id") Long id,@RequestParam(value = "level") String level) {

        if("sub".equals(level)){

            List<Category> categoryList = categoryService.getSubCatagoryById(id);

            return new Response(0, "成功", categoryList);
        }

        return new Response(0, "失败", "");
    }


    @ApiOperation(produces = "application/json", value = "更改类目")
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    @ResponseBody
    public Response<Category> updateCategory(@PathVariable("id") Long id,@RequestBody Category category) {


        category.setId(id);

        boolean isSuccess = categoryService.updateCategory(category);


        if (isSuccess) {

            return new Response(0, "成功", "");
        } else {

            return new Response(0, "失败", "");
        }

    }
    

}
