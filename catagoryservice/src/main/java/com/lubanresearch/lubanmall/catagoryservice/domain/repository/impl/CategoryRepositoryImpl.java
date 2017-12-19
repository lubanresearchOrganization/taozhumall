package com.lubanresearch.lubanmall.catagoryservice.domain.repository.impl;

import com.lubanresearch.lubanmall.catagoryservice.domain.Category;
import com.lubanresearch.lubanmall.catagoryservice.domain.query.condition.CatagoryQueryCondition;
import com.lubanresearch.lubanmall.catagoryservice.domain.repository.CategoryRepository;
import com.lubanresearch.lubanmall.catagoryservice.infrastructure.persistence.db.mapper.CatagoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author zyf
 */
@Repository
public class CategoryRepositoryImpl implements CategoryRepository {


    @Autowired
    CatagoryMapper catagoryMapper;

    @Override
    public boolean addCatagory(Category category) {

        int result = catagoryMapper.insert(category);

        if (result == 1) {

            return true;
        }
        return false;
    }

    @Override
    public Category getById(Long id) {
        return catagoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Category> getTopCategory() {
        return catagoryMapper.selectByExample(
                new CatagoryQueryCondition()
                        .createCriteria()
                        .andParentIdEqualTo(new Long(0))
                        .example()
        );

    }

    @Override
    public List<Category> getSubCategoryById(Long id) {
        return catagoryMapper.selectByExample(
                new CatagoryQueryCondition()
                        .createCriteria()
                        .andParentIdEqualTo(id)
                        .example()
        );
    }

    @Override
    public boolean updateCategory(Category category) {

        int result =catagoryMapper.updateByPrimaryKey(category);

        if (result == 1) {

            return true;
        }
        return false;

    }

}
