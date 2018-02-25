package com.lubanresearch.lubanmall.merchantservice.application.controller;

import com.lubanresearch.lubanmall.common.bean.Pagination;
import com.lubanresearch.lubanmall.merchantservice.domain.Shop;
import com.lubanresearch.lubanmall.merchantservice.infrastructure.persistence.db.mapper.ShopMapper;
import com.lubanresearch.lubanmall.merchantservice.infrastructure.persistence.db.query.condition.ShopQueryCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {


        ShopQueryCondition.Criteria shopCriteria = new ShopQueryCondition().createCriteria();

        Pagination<Shop> pagination = new Pagination<>();

        pagination.setItems(shopMapper.selectByExample(shopCriteria.example().page(page, size).orderBy("create_time desc")));
        pagination.setTotal((int) shopMapper.countByExample(shopCriteria.example()));
        pagination.setPageCount((pagination.getTotal() % size == 0) ? (pagination.getTotal() / size) : (pagination.getTotal() / size + 1));
        pagination.setPageIndex(page);
        return pagination;
    }
}
