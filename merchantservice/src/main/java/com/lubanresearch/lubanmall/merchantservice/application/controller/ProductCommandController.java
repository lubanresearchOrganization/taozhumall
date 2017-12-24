package com.lubanresearch.lubanmall.merchantservice.application.controller;

import com.lubanmall.merchantserviceapi.bean.ProductDTO;
import com.lubanresearch.lubanmall.common.bean.Response;
import com.lubanresearch.lubanmall.merchantservice.domain.Product;
import com.lubanresearch.lubanmall.merchantservice.domain.command.AddProductCommand;
import com.lubanresearch.lubanmall.merchantservice.domain.command.RemoveProductCommand;
import com.lubanresearch.lubanmall.merchantservice.domain.command.UpdateProductCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hilbertcao on 2017/12/19.
 */
@Controller
@RequestMapping("/v/0.1")
public class ProductCommandController {

    @Autowired
    private CommandGateway commandGateway;

    @RequestMapping(value = "/products",method = RequestMethod.POST)
    @ResponseBody
    public Response<Product> addProduct(@RequestBody ProductDTO dto){

        Product product = commandGateway.sendAndWait(new AddProductCommand(dto.getName(),dto.getImgUrl(),dto.getUnitPrice(),dto.getShopId(),dto.getCategoryId()));
      return new Response<>(0,"success",product);
    }

    @RequestMapping(value = "/products/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public Response removeProduct(@PathVariable("id") Long id){



        Product product = commandGateway.sendAndWait(new RemoveProductCommand(id));
        return new Response<>(0,"success",null);
    }

    @RequestMapping(value = "/products/{id}",method = RequestMethod.PATCH)
    @ResponseBody
    public Response<Product> updateProduct(@PathVariable("id") Long id,@RequestBody ProductDTO dto){



        Product product = commandGateway.sendAndWait(new UpdateProductCommand(id,dto.getName(),dto.getImgUrl(),dto.getUnitPrice(),dto.getCategoryId()));
        return new Response<>(0,"success",product);
    }

}
