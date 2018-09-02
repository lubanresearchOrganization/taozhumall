package com.lubanresearch.lubanmall.merchantservice.application.controller;

import com.lubanmall.categoryserviceapi.bean.CategoryDTO;
import com.lubanresearch.lubanmall.common.bean.Pagination;
import com.lubanresearch.lubanmall.merchantservice.domain.Product;
import com.lubanresearch.lubanmall.merchantservice.infrastructure.persistence.db.mapper.ProductMapper;
import com.lubanresearch.lubanmall.merchantservice.infrastructure.persistence.db.query.condition.ProductQueryCondition;
import com.lubanresearch.lubanmall.merchantservice.infrastructure.remote.CategoryService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hilbertcao on 2017/12/16.
 */
@Controller
@RequestMapping("/v/0.1/products")
public class ProductQueryController {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Product getProduct(@PathVariable("id") Long id) {


        return productMapper.selectByPrimaryKey(id);
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Pagination<Product> findProducts(
            @RequestParam(value = "ids",required = false) List<Long> ids,
            @RequestParam(value = "shopId",required = false) Long shopId,
            @RequestParam(value = "categoryId",required = false) Long categoryId,
            @RequestParam(value = "recursive",defaultValue = "false")boolean recursive,
            @RequestParam(value = "productIds",required = false) List<Long> productIds,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {

        ProductQueryCondition condition = new ProductQueryCondition();
        ProductQueryCondition.Criteria criteria = condition.createCriteria().andIf(shopId != null, new ProductQueryCondition.Criteria.ICriteriaAdd() {
            @Override
            public ProductQueryCondition.Criteria add(ProductQueryCondition.Criteria add) {
                return add.andShopIdEqualTo(shopId);
            }
        }).andIf(CollectionUtils.isNotEmpty(productIds) , new ProductQueryCondition.Criteria.ICriteriaAdd() {
            @Override
            public ProductQueryCondition.Criteria add(ProductQueryCondition.Criteria add) {
                return add.andIdIn(productIds);
            }
        }).andIf(!recursive&&categoryId != null,new ProductQueryCondition.Criteria.ICriteriaAdd() {
            @Override
            public ProductQueryCondition.Criteria add(ProductQueryCondition.Criteria add) {
                return add.andCategoryIdEqualTo(categoryId);
            }
        });

        if(recursive){
            List<Long> categoryIds = categoryService.getCategorys(categoryId,true).stream().map(CategoryDTO::getId).collect(Collectors.toList());
            categoryIds.add(categoryId);
            criteria.andIf(categoryIds.size()>0, new ProductQueryCondition.Criteria.ICriteriaAdd() {
                @Override
                public ProductQueryCondition.Criteria add(ProductQueryCondition.Criteria add) {
                    return add.andCategoryIdIn(categoryIds);
                }
            });
        }
        boolean forPage = size>0&&page>0;
        if(forPage){
            condition.limit(page*size,size);
        }
        condition.orderBy("create_time desc");

        Pagination<Product> productPagination = new Pagination<>();
        productPagination.setItems(productMapper.selectByExample(condition));
        if(forPage){
            productPagination.setTotal((int) productMapper.countByExample(condition));
            productPagination.setPageCount((productPagination.getTotal() % size == 0) ? (productPagination.getTotal() / size) : (productPagination.getTotal() / size + 1));
            productPagination.setPageIndex(page);
        }
        return productPagination;
    }
}
