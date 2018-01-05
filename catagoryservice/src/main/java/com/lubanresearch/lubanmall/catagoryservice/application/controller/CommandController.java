package com.lubanresearch.lubanmall.catagoryservice.application.controller;


import com.lubanmall.catagoryserviceapi.bean.CategoryDTO;
import com.lubanresearch.lubanmall.catagoryservice.domain.Category;
import com.lubanresearch.lubanmall.catagoryservice.domain.command.AddCategoryCommand;
import com.lubanresearch.lubanmall.catagoryservice.domain.command.UpdateCategoryCommand;
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
    public Category addCategory(@RequestBody CategoryDTO category) {

        return commandGateway.sendAndWait(new AddCategoryCommand(category.getName(), category.getParentId()));

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    @ResponseBody
    public Category updateCategory(@PathVariable("id") Long id, @RequestBody CategoryDTO command) {

        return commandGateway.sendAndWait(new UpdateCategoryCommand(id, command.getParentId(), command.getName()));

    }


}
