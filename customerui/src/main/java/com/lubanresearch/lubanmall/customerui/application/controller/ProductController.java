package com.lubanresearch.lubanmall.customerui.application.controller;

import com.lubanmall.merchantserviceapi.bean.ProductDTO;
import com.lubanresearch.lubanmall.common.bean.Pagination;
import com.lubanresearch.lubanmall.customerui.infrastructure.remote.ProductService;
import com.lubanresearch.lubanmall.customerui.infrastructure.remote.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hilbertcao on 2018/1/30.
 */
@Controller
@RequestMapping("/v/0.1/products")
public class ProductController {

    @Autowired
    private SearchService searchService;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    Pagination<ProductDTO> findProducts(
            @RequestParam(value = "categoryId",required = false)Long categoryId,
            @RequestParam(value = "shopId",required = false)Long shopId,
            @RequestParam(value = "key",required = false) String  key,
            @RequestParam(value = "recursive",defaultValue = "false")boolean recursive,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "type")String type
    ){

        if("keySearch".equals(type)){

            return searchService.findProducts(key, page, size);
        }else if("query".equals(type)){

            return productService.findProducts(categoryId, shopId, recursive, page, size);
        }
        return null;
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    @ResponseBody
    ProductDTO getProduct(
            @PathVariable(value = "productId")Long productId
    ){

        return productService.getProduct(productId);
    }
}
