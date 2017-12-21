package com.lubanresearch.lubanmall.catagoryservice.domain;

import com.lubanresearch.lubanmall.catagoryservice.domain.Category;

import com.lubanresearch.lubanmall.catagoryservice.infrastructure.persistence.db.mapper.CategoryMapper;
import com.lubanresearch.lubanmall.catagoryservice.infrastructure.persistence.db.query.condition.CategoryQueryCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


/**
 * Created by zyf on 2017/12/21
 */
@Repository
public class CategoryRepository {


    @Autowired
    CategoryMapper categoryMapper;


    public Category addCatagory(Category category) {

        category.setCreateTime(new Date());
        category.setId(System.currentTimeMillis());
        categoryMapper.insertSelective(category);

        return category;
    }


    public Category getByName(String name) {
        CategoryQueryCondition catagoryQueryCondition = new CategoryQueryCondition();
        catagoryQueryCondition.createCriteria().andNameEqualTo(name);
        List<Category> categoryList = categoryMapper.selectByExample(catagoryQueryCondition);
        if(categoryList.isEmpty()){
            return null;
        }
        return categoryList.get(0);
    }

    public Category getById(Long id) {
        return categoryMapper.selectByPrimaryKey(id);
    }


    public List<Category> getCategoryByParentId(Long parentId) {
        return categoryMapper.selectByExample(
                new CategoryQueryCondition()
                        .createCriteria()
                        .andParentIdEqualTo(parentId)
                        .example()
        );
    }




    public List<Category> getSubCategoryById(Long id) {

        return categoryMapper.selectByExample(
                new CategoryQueryCondition()
                        .createCriteria()
                        .andParentIdEqualTo(id)
                        .example()
        );

    }


    public void update(Category category) {

        categoryMapper.updateByPrimaryKey(category);

    }

}
