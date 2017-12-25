package com.lubanresearch.lubanmall.catagoryservice.application.controller;


import com.lubanmall.catagoryserviceapi.bean.CategoryDTO;
import com.lubanresearch.lubanmall.catagoryservice.domain.Category;
import com.lubanresearch.lubanmall.catagoryservice.domain.command.AddCategoryCommand;
import com.lubanresearch.lubanmall.catagoryservice.domain.command.UpdateCategoryCommand;
import com.lubanresearch.lubanmall.common.bean.Response;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zyf on 2017/12/21.
 */
@Controller
@RequestMapping("/v/0.1/categorys")
public class CommandController {

    @Autowired
    private CommandGateway commandGateway;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Response<Category> addCategory(@RequestBody CategoryDTO category) {

        return new Response<>(0, "success", commandGateway.sendAndWait(new AddCategoryCommand(category.getName(), category.getParentId())));

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    @ResponseBody
    public Response<Category> updateCategory(@PathVariable("id") Long id, @RequestBody CategoryDTO command) {

        return new Response<>(0, "success", commandGateway.sendAndWait(new UpdateCategoryCommand(id, command.getParentId(), command.getName())));

    }


}
