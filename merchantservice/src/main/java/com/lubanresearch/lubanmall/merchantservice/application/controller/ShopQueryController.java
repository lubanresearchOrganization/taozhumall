package com.lubanresearch.lubanmall.merchantservice.application.controller;

import com.lubanresearch.lubanmall.common.bean.Pagination;
import com.lubanresearch.lubanmall.common.bean.Response;
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

    @RequestMapping(value = "shops/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Response<Shop> getShop(@PathVariable("id") Long id) {


        return new Response<>(0, "success", shopMapper.selectByPrimaryKey(id));
    }

    @RequestMapping(value = "shops", method = RequestMethod.GET)
    @ResponseBody
    public Response<Pagination<Shop>> findShops(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {

        ShopQueryCondition condition = new ShopQueryCondition();
        condition.orderBy("create_time desc");

        Pagination<Shop> pagination = new Pagination<>();
        pagination.setItems(shopMapper.selectByExample(condition));
        pagination.setTotal((int) shopMapper.countByExample(condition));
        pagination.setPageCount((pagination.getTotal() % size == 0) ? (pagination.getTotal() / size) : (pagination.getTotal() / size + 1));
        pagination.setPageIndex(page);
        return new Response<>(0, "success", pagination);
    }
}
