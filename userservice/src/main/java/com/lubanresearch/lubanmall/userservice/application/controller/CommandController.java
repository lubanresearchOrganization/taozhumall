package com.lubanresearch.lubanmall.userservice.application.controller;

import com.lubanmall.userserviceapi.bean.UserDTO;
import com.lubanmall.userserviceapi.bean.UserType;
import com.lubanmall.userserviceapi.command.ChangepasswordCommandDTO;
import com.lubanresearch.lubanmall.userservice.domain.User;
import com.lubanresearch.lubanmall.userservice.domain.command.AddUserCommand;
import com.lubanresearch.lubanmall.userservice.domain.command.ChangepasswordCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hilbertcao on 2017/12/19.
 */
@Controller
@RequestMapping("/v/0.1/users")
public class CommandController {

    @Autowired
    private CommandGateway commandGateway;

    @RequestMapping(value = "/",method = RequestMethod.POST)
    @ResponseBody
    public User addUser(@RequestBody UserDTO user){

        return commandGateway.sendAndWait(new AddUserCommand(user.getName(),user.getPassword(),user.getMobile(),user.getType()));
    }

    @RequestMapping(value = "/users/{id}/commands/changePassword",method = RequestMethod.POST)
    @ResponseBody
    public User changePassword(@PathVariable("id")Long id,@RequestBody ChangepasswordCommandDTO command){

        return commandGateway.sendAndWait(new ChangepasswordCommand(command.getId(),command.getNewPassword(),command.getOldPassword()));
    }

    @RequestMapping(value = "/a",method = RequestMethod.GET)
    @ResponseBody
    public User a(){

        return commandGateway.sendAndWait(new AddUserCommand(null,"c","136789999878", UserType.CUSTOMER.getValue()));
    }

    @RequestMapping(value = "/b",method = RequestMethod.GET)
    @ResponseBody
    public User b(){

        return commandGateway.sendAndWait(new ChangepasswordCommand(1513709082550L,"d","b"));
    }


}
