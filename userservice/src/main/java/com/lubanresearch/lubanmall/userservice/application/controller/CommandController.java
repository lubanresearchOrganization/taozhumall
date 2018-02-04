package com.lubanresearch.lubanmall.userservice.application.controller;

import com.lubanmall.userserviceapi.bean.UserDTO;
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

        String name = user.getName();
        return commandGateway.sendAndWait(new AddUserCommand(user.getName(),user.getPassword(),user.getMobile(),user.getType()));
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public void updateUser(@RequestBody UserDTO user){

    }

    @RequestMapping(value = "/{id}/commands/changePassword",method = RequestMethod.POST)
    @ResponseBody
    public User changePassword(@PathVariable("id")Long id,@RequestBody ChangepasswordCommandDTO command){

        return commandGateway.sendAndWait(new ChangepasswordCommand(id,command.getNewPassword(),command.getOldPassword()));
    }
}
