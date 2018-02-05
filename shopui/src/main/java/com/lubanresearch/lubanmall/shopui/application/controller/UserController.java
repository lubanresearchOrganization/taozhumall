package com.lubanresearch.lubanmall.shopui.application.controller;

import com.lubanmall.userserviceapi.bean.UserDTO;
import com.lubanmall.userserviceapi.command.ChangepasswordCommandDTO;
import com.lubanresearch.lubanmall.shopui.infrastructure.remote.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hilbertcao on 2018/2/4.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public void updateUser(@RequestBody UserDTO user){
        userService.updateUser(user);
    }

    @RequestMapping(value = "/{id}/commands/changePassword",method = RequestMethod.POST)
    @ResponseBody
    public UserDTO changePassword(@PathVariable("id")Long id, @RequestBody ChangepasswordCommandDTO command){

        return userService.changePassword(id,command);
    }
}
