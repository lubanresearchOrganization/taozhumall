package com.lubanresearch.lubanmall.catagoryservice.application.controller;


import com.lubanresearch.lubanmall.catagoryservice.domain.Category;
import com.lubanresearch.lubanmall.catagoryservice.service.CategoryService;
import com.lubanresearch.lubanmall.common.bean.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Api(tags = "category", description = "类目控制器")
@Controller
@RequestMapping("/category")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @ApiOperation(produces = "application/json", value = "添加类目")
    @RequestMapping(value = "add/{id}/{name}/{parent_id}", method = RequestMethod.POST)
    @ResponseBody
    public Response<Category> insert(@PathVariable("id") Long id, @PathVariable("name") String name, @PathVariable("parent_id") Long parent_id) {


        Category category = new Category();
        category.setId(id);
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

    @ApiOperation(produces = "application/json", value = "获得顶级类目")
    @RequestMapping(value = "getTopCategory", method = RequestMethod.GET)
    @ResponseBody
    public Response<Category> getTopCategory() {


        List<Category> categoryList = categoryService.getTopLevelCatagory();

        return new Response(0, "成功", categoryList);


    }


    @ApiOperation(produces = "application/json", value = "通过id获得子类目")
    @RequestMapping(value = "getSubCategoryById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Response<Category> getSubCategoryById(@PathVariable("id") Long id) {


        List<Category> categoryList = categoryService.getSubCatagoryById(id);

        return new Response(0, "成功", categoryList);

    }


    @ApiOperation(produces = "application/json", value = "更改类目名称")
    @RequestMapping(value = "updateCategoryName/{id}/{name}", method = RequestMethod.PUT)
    @ResponseBody
    public Response<Category> updateCategoryName(@PathVariable("id") Long id, @PathVariable("name") String name) {


        Category category = new Category();
        category.setId(id);
        category.setName(name);


        boolean isSuccess = categoryService.updateCategory(category);


        if (isSuccess) {

            return new Response(0, "成功", "");
        }else{

            return new Response(0, "失败", "");
        }

    }



    @ApiOperation(produces = "application/json", value = "更改类目父类目")
    @RequestMapping(value = "/updateCategoryParent/{id}/{parent_id}", method = RequestMethod.PUT)
    @ResponseBody
    public Response<Category> updateCategoryParent(@PathVariable("id") Long id, @PathVariable("parent_id") Long parent_id) {


        Category category = new Category();
        category.setId(id);
        category.setParentId(parent_id);


        boolean isSuccess = categoryService.updateCategory(category);


        if (isSuccess) {

            return new Response(0, "成功", "");
        }else{

            return new Response(0, "失败", "");
        }

    }

}
