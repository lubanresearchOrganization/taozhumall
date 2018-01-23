package com.lubanresearch.lubanmall.catagoryservice.application.controller;


import com.lubanresearch.lubanmall.catagoryservice.domain.Category;
import com.lubanresearch.lubanmall.catagoryservice.infrastructure.persistence.db.mapper.CategoryMapper;
import com.lubanresearch.lubanmall.catagoryservice.infrastructure.persistence.db.query.condition.CategoryQueryCondition;
import com.lubanresearch.lubanmall.common.bean.Response;
import com.lubanresearch.lubanmall.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Category getCategoryById(@PathVariable("id") Long id) {


        return categoryMapper.selectByPrimaryKey(id);
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> getCategorys(@RequestParam(value = "parentId",required = false) Long parentId) {


        CategoryQueryCondition.Criteria criteria = new CategoryQueryCondition().createCriteria();
        if(parentId!=null){
            criteria.andParentIdEqualTo(parentId);
        }
        return categoryMapper.selectByExample(
                criteria.example()
        );

    }


}
