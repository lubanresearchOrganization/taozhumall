package com.lubanresearch.lubanmall.searchservice.application.controller;

import com.lubanresearch.lubanmall.common.bean.Pagination;
import com.lubanresearch.lubanmall.searchservice.domain.Shop;
import com.lubanresearch.lubanmall.searchservice.infrastructure.persistence.db.mapper.ShopMapper;
import com.lubanresearch.lubanmall.searchservice.infrastructure.persistence.db.query.condition.ShopQueryCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hilbertcao on 2017/12/16.
 */
@Controller
@RequestMapping("/v/0.1/search/shops")
public class ShopQueryController {

    @Autowired
    private ShopMapper shopMapper;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Pagination<Shop> findShops(
            @RequestParam(value = "key",required = false) String  key,
            @RequestParam(value = "page", defaultValue = "0",required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "10",required = false) Integer size
    ) {

        ShopQueryCondition condition = new ShopQueryCondition();
        condition.createCriteria().andIf(key != null&&!"".equals(key), new ShopQueryCondition.Criteria.ICriteriaAdd() {
            @Override
            public ShopQueryCondition.Criteria add(ShopQueryCondition.Criteria add) {
                return add.andNameLike("%"+key+"%");
            }
        });
        condition.orderBy("create_time desc");
        condition.limit(page*size,size);
        Pagination<Shop> pagination = new Pagination<>();
        pagination.setItems(shopMapper.selectByExample(condition));
        pagination.setTotal((int) shopMapper.countByExample(condition));
        pagination.setSize(size);
        pagination.setPageCount((pagination.getTotal() % size == 0) ? (pagination.getTotal() / size) : (pagination.getTotal() / size + 1));
        pagination.setPageIndex(page);
        return pagination;
    }
}
