package com.lubanresearch.lubanmall.catagoryservice.application.controller;


import com.lubanresearch.lubanmall.catagoryservice.domain.Category;
import com.lubanresearch.lubanmall.catagoryservice.infrastructure.persistence.db.mapper.CategoryMapper;
import com.lubanresearch.lubanmall.catagoryservice.infrastructure.persistence.db.query.condition.CategoryQueryCondition;
import com.lubanresearch.lubanmall.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static com.lubanresearch.lubanmall.catagoryservice.domain.Category.Column.parentId;

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
    public Category getCategoryById(@PathVariable("id") Long id) {


        return categoryMapper.selectByPrimaryKey(id);
    }


    @RequestMapping("/topLevel")
    @ResponseBody
    public List<Category> getTopLevel() {


        return categoryMapper.selectByExample(
                new CategoryQueryCondition()
                        .createCriteria()
                        .andParentIdEqualTo(new Long(0))
                        .example()
        );

    }


    @RequestMapping("/{id}/subCategory")
    @ResponseBody
    public List<Category> getSubCategoryById(@PathVariable("id") Long id) {


        return categoryMapper.selectByExample(
                new CategoryQueryCondition()
                        .createCriteria()
                        .andParentIdEqualTo(id)
                        .example()
        );
    }

}
