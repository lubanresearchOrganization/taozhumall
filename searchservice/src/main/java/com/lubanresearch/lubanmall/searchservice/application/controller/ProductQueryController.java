package com.lubanresearch.lubanmall.searchservice.application.controller;

import com.lubanmall.catagoryserviceapi.bean.CategoryDTO;
import com.lubanresearch.lubanmall.common.bean.Pagination;
import com.lubanresearch.lubanmall.common.bean.Response;
import com.lubanresearch.lubanmall.searchservice.domain.Product;
import com.lubanresearch.lubanmall.searchservice.infrastructure.persistence.db.mapper.ProductMapper;
import com.lubanresearch.lubanmall.searchservice.infrastructure.persistence.db.query.condition.ProductQueryCondition;
import com.lubanresearch.lubanmall.searchservice.infrastructure.remote.CatagoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hilbertcao on 2017/12/16.
 */
@Controller
@RequestMapping("/v/0.1/search/products")
public class ProductQueryController {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CatagoryService catagoryService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Pagination<Product> findProducts(
            @RequestParam(value = "categoryId",required = false)Long categoryId,
            @RequestParam(value = "key",required = false) String  key,
            @RequestParam(value = "recursive",defaultValue = "false")boolean recursive,
            @RequestParam(value = "page", defaultValue = "0",required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "10",required = false) Integer size
    ) {

        ProductQueryCondition condition = new ProductQueryCondition();
        condition.createCriteria().andIf(key != null&&!"".equals(key), new ProductQueryCondition.Criteria.ICriteriaAdd() {
            @Override
            public ProductQueryCondition.Criteria add(ProductQueryCondition.Criteria add) {
                return add.andNameLike(key);
            }
        });
        if(!recursive){
            condition.createCriteria().andIf(categoryId != null, new ProductQueryCondition.Criteria.ICriteriaAdd() {
                @Override
                public ProductQueryCondition.Criteria add(ProductQueryCondition.Criteria add) {
                    return add.andCategoryIdEqualTo(categoryId);
                }
            });
        }else{

            List<Long> catagoryIds = catagoryService.getCategorys(categoryId,true).stream().map(CategoryDTO::getId).collect(Collectors.toList());
            condition.createCriteria().andIf(catagoryIds.size()>0, new ProductQueryCondition.Criteria.ICriteriaAdd() {
                @Override
                public ProductQueryCondition.Criteria add(ProductQueryCondition.Criteria add) {
                    return add.andCategoryIdIn(catagoryIds);
                }
            });
        }
        condition.orderBy("create_time desc");

        Pagination<Product> productPagination = new Pagination<>();
        productPagination.setItems(productMapper.selectByExample(condition));
        productPagination.setTotal((int) productMapper.countByExample(condition));
        productPagination.setSize(size);
        productPagination.setPageCount((productPagination.getTotal() % size == 0) ? (productPagination.getTotal() / size) : (productPagination.getTotal() / size + 1));
        productPagination.setPageIndex(page);
        return productPagination;
    }
}
