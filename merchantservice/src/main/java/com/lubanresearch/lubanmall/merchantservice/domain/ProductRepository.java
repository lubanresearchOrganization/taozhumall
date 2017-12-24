package com.lubanresearch.lubanmall.merchantservice.domain;

import com.lubanresearch.lubanmall.merchantservice.infrastructure.persistence.db.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by hilbertcao on 2017/12/25.
 */
@Repository
public class ProductRepository {

    @Autowired
    private ProductMapper productMapper;

    public void add(Product product) {
        productMapper.insertSelective(product);
    }

    public void update(Product product) {
        productMapper.updateByPrimaryKeySelective(product);
    }

    public void remove(Long id) {
        productMapper.deleteByPrimaryKey(id);
    }

    public Product get(Long id) {

        return productMapper.selectByPrimaryKey(id);
    }
}
