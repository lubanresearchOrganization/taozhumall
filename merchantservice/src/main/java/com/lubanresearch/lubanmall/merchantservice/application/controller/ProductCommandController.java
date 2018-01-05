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
@RequestMapping("/v/0.1/prodcuts")
public class ProductCommandController {

    @Autowired
    private CommandGateway commandGateway;

    @RequestMapping(value = "/",method = RequestMethod.POST)
    @ResponseBody
    public Product addProduct(@RequestBody ProductDTO dto){

        return commandGateway.sendAndWait(new AddProductCommand(dto.getName(),dto.getImgUrl(),dto.getUnitPrice(),dto.getShopId(),dto.getCategoryId()));
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public void removeProduct(@PathVariable("id") Long id){



        commandGateway.sendAndWait(new RemoveProductCommand(id));
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PATCH)
    @ResponseBody
    public Response<Product> updateProduct(@PathVariable("id") Long id,@RequestBody ProductDTO dto){



        return commandGateway.sendAndWait(new UpdateProductCommand(id,dto.getName(),dto.getImgUrl(),dto.getUnitPrice(),dto.getCategoryId()));
    }

}
