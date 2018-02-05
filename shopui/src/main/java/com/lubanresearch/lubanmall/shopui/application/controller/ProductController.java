package com.lubanresearch.lubanmall.shopui.application.controller;

import com.lubanmall.merchantserviceapi.bean.ProductDTO;
import com.lubanresearch.lubanmall.shopui.infrastructure.remote.ProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(tags = "product" , description = "产品")
@Controller
@RequestMapping("/v/0.1/products")
public class ProductController {


    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/",method = RequestMethod.POST)
    @ResponseBody
    public ProductDTO addProduct(@RequestBody ProductDTO dto){

        return productService.addProduct(dto);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public void removeProduct(@PathVariable("id") Long id){


        productService.removeProduct(id);

    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PATCH)
    @ResponseBody
    public ProductDTO updateProduct(@PathVariable("id") Long id,@RequestBody ProductDTO dto){


        return productService.updateProduct(id, dto);
    }
}
