package com.lubanresearch.lubanmall.catagoryservice.service.impl;

import com.lubanresearch.lubanmall.catagoryservice.domain.Category;
import com.lubanresearch.lubanmall.catagoryservice.domain.repository.CategoryRepository;
import com.lubanresearch.lubanmall.catagoryservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zyf
 */


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getTopLevelCatagory() {
        return categoryRepository.getTopCategory();
    }

    @Override
    public List<Category> getSubCatagoryById(Long id) {
        return categoryRepository.getSubCategoryById(id);
    }

    @Override
    public boolean addCatagory(Category category) {

        if (categoryRepository.getById(category.getId()) == null) {

            return categoryRepository.addCatagory(category);

        } else {

            return false;

        }


    }

    @Override
    public boolean updateCategory(Category category) {


        Category oldCategory = categoryRepository.getById(category.getId());

        if(oldCategory == null){

            return false;

        }

        if(category.getParentId() == null){

            category.setParentId(oldCategory.getParentId());

        }

        if(category.getName() ==  null){

            category.setName(oldCategory.getName());

        }

        category.setCreateTime(oldCategory.getCreateTime());


        return categoryRepository.updateCategory(category);


    }



}
