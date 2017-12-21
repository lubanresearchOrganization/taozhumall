package com.lubanresearch.lubanmall.catagoryservice.application.controller;


import com.lubanmall.catagoryserviceapi.bean.CategoryDTO;
import com.lubanmall.catagoryserviceapi.command.ChangeCategoryNameDTO;
import com.lubanresearch.lubanmall.catagoryservice.domain.Category;
import com.lubanresearch.lubanmall.catagoryservice.domain.command.AddCategoryCommand;
import com.lubanresearch.lubanmall.catagoryservice.domain.command.ChangeCategoryNameCommand;
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

    @RequestMapping(value = "/commands/addCategory",method = RequestMethod.POST)
    @ResponseBody
    public Category addCategory(@RequestBody CategoryDTO category){

        return commandGateway.sendAndWait(new AddCategoryCommand(category.getName(),category.getParentId()));
    }



    @RequestMapping(value = "/{id}/commands/changeName",method = RequestMethod.POST)
    @ResponseBody
    public Category changeName(@PathVariable("id")Long id, @RequestBody ChangeCategoryNameDTO command){

        return commandGateway.sendAndWait(new ChangeCategoryNameCommand(command.getId(),command.getName()));
    }
}
