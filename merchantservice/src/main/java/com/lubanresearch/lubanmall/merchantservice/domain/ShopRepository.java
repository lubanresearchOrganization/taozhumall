package com.lubanresearch.lubanmall.merchantservice.domain;

import com.lubanresearch.lubanmall.merchantservice.infrastructure.persistence.db.mapper.ShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by hilbertcao on 2017/12/25.
 */
@Repository
public class ShopRepository {
    @Autowired
    private ShopMapper shopMapper;
    public void add(Shop shop) {
        shopMapper.insertSelective(shop);
    }

    public Shop get(Long id) {
        return shopMapper.selectByPrimaryKey(id);
    }

    public void remove(Long id) {
        shopMapper.deleteByPrimaryKey(id);
    }

    public void update(Shop shop) {
        shopMapper.updateByPrimaryKeySelective(shop);
    }
}
