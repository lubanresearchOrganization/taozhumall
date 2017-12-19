package com.lubanresearch.lubanmall.catagoryservice.domain.repository;

import com.lubanresearch.lubanmall.catagoryservice.domain.Category;

import java.util.List;

public interface CategoryRepository {

    boolean addCatagory(Category category);

    Category getById(Long id);

    List<Category> getCategoryByParentId(Long parentId);

    List<Category> getSubCategoryById(Long id);


    boolean updateCategory(Category category);

}
