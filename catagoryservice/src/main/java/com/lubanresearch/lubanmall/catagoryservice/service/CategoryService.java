package com.lubanresearch.lubanmall.catagoryservice.service;

import com.lubanresearch.lubanmall.catagoryservice.domain.Category;

import java.util.List;

/**
 * @author zyf
 */
public interface CategoryService {


    List<Category> getTopLevelCatagory();

    List<Category> getSubCatagoryById(Long id);

    boolean addCatagory(Category category);


    boolean updateCategory(Category category);


}
