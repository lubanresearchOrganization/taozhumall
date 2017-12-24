package com.lubanresearch.lubanmall.merchantservice.domain.commandhandler;

import com.lubanresearch.lubanmall.merchantservice.domain.Product;
import com.lubanresearch.lubanmall.merchantservice.domain.ProductRepository;
import com.lubanresearch.lubanmall.merchantservice.domain.command.UpdateProductCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by hilbertcao on 2017/12/25.
 */
@Component
public class UpdateProductCommandHandler {

    @Autowired
    private ProductRepository productRepository;

    public Product handler(UpdateProductCommand command){

        Product product = new Product();
        product.setName(command.getName());
        product.setCategoryId(command.getCategoryId());
        product.setImgUrl(command.getImgUrl());
        product.setId(command.getId());
        product.setUnitPrice(command.getUnitPrice());
        product.setCreateTime(new Date());
        productRepository.update(product);
        return productRepository.get(product.getId());
    }
}
