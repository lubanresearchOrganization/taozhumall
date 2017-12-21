package com.lubanresearch.lubanmall.catagoryservice.application.controller;


import com.lubanresearch.lubanmall.catagoryservice.domain.Category;
import com.lubanresearch.lubanmall.catagoryservice.infrastructure.persistence.db.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zyf on 2017/12/21
 */
@Controller
@RequestMapping("/v/0.1/categorys")
public class QueryController {


    @Autowired
    private CategoryMapper categoryMapper;


    @RequestMapping("/{id}")
    @ResponseBody
    public Category getCategoryById(@PathVariable("id") Long id){


        return categoryMapper.selectByPrimaryKey(id);
    }
}
