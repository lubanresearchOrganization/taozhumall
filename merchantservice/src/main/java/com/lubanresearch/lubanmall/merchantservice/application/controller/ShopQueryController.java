package com.lubanresearch.lubanmall.merchantservice.application.controller;

import com.lubanresearch.lubanmall.common.bean.Pagination;
import com.lubanresearch.lubanmall.merchantservice.domain.Shop;
import com.lubanresearch.lubanmall.merchantservice.infrastructure.persistence.db.mapper.ShopMapper;
import com.lubanresearch.lubanmall.merchantservice.infrastructure.persistence.db.query.condition.ShopQueryCondition;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hilbertcao on 2017/12/16.
 */
@Controller
@RequestMapping("/v/0.1/shops")
public class ShopQueryController {

    @Autowired
    private ShopMapper shopMapper;


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Shop getShop(@PathVariable("id") Long id) {


        return shopMapper.selectByPrimaryKey(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Pagination<Shop> findShops(
            @RequestParam(value = "ids", required = false) List<Long> ids,
            @RequestParam(value = "page", defaultValue = "0",required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "10",required = false) Integer size
    ) {


        ShopQueryCondition.Criteria shopCriteria = new ShopQueryCondition().createCriteria();
        shopCriteria.andIf(CollectionUtils.isNotEmpty(ids),new ShopQueryCondition.Criteria.ICriteriaAdd(){

            @Override
            public ShopQueryCondition.Criteria add(ShopQueryCondition.Criteria add) {
                return add.andIdIn(ids);
            }
        });
        boolean forPage = size>0&&page>0;
        if(forPage){
            shopCriteria.example().page(page, size);
        }

        Pagination<Shop> pagination = new Pagination<>();

        pagination.setItems(shopMapper.selectByExample(shopCriteria.example().orderBy("create_time desc")));
        if(forPage){
            pagination.setTotal((int) shopMapper.countByExample(shopCriteria.example()));
            pagination.setPageCount((pagination.getTotal() % size == 0) ? (pagination.getTotal() / size) : (pagination.getTotal() / size + 1));
            pagination.setPageIndex(page);
        }

        return pagination;
    }
}
