package com.lubanresearch.lubanmall.merchantservice.domain.commandhandler;

import com.lubanresearch.lubanmall.merchantservice.domain.Product;
import com.lubanresearch.lubanmall.merchantservice.domain.ProductRepository;
import com.lubanresearch.lubanmall.merchantservice.domain.command.AddProductCommand;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by hilbertcao on 2017/12/25.
 */
@Component
public class AddProductCommandHandler {

    @Autowired
    private ProductRepository productRepository;
    @CommandHandler
    public Product handler(AddProductCommand command){

        Product product = new Product();
        product.setId(System.currentTimeMillis());
        product.setName(command.getName());
        product.setCategoryId(command.getCategoryId());
        product.setImgUrl(command.getImgUrl());
        product.setShopId(command.getShopId());
        product.setUnitPrice(command.getUnitPrice());
        product.setCreateTime(new Date());
        productRepository.add(product);
        return productRepository.get(product.getId());
    }


}
