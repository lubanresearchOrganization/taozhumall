package com.lubanresearch.lubanmall.catagoryservice.application.controller;


import com.lubanresearch.lubanmall.catagoryservice.domain.Category;
import com.lubanresearch.lubanmall.catagoryservice.infrastructure.persistence.db.mapper.CategoryMapper;
import com.lubanresearch.lubanmall.catagoryservice.infrastructure.persistence.db.query.condition.CategoryQueryCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public List<Category> getCategorys(@RequestParam(value = "parentId",required = false) Long parentId,
                                       @RequestParam(value = "recursive",defaultValue = "false",required = false)boolean recursive) {


        if(parentId==null){
            return getDirectChildren(null);
        }
        if(recursive){

            return getSuccessors(parentId);
        }
        return getDirectChildren(parentId);

    }

    private List<Category> getSuccessors(Long parentId){

        List<Category> result = new ArrayList<>();
        fillSuccessors(parentId,result);
        return result;

    }

    private void fillSuccessors(Long parentId,List<Category> result){

        List<Category> children = getDirectChildren(parentId);
        if(children.size()>0){
            result.addAll(children);
            children.forEach(child->{
                fillSuccessors(child.getId(),result);
            });
        }
    }

    private List<Category> getDirectChildren(Long parentId){
        CategoryQueryCondition.Criteria criteria = new CategoryQueryCondition().createCriteria();
        if(parentId!=null){
            criteria.andParentIdEqualTo(parentId);
        }
        return categoryMapper.selectByExample(
                criteria.example()
        );
    }

}
