package com.lubanresearch.lubanmall.customerui.application.controller;

import com.lubanmall.userserviceapi.bean.UserDTO;
import com.lubanresearch.lubanmall.ssoclient.bean.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by hilbertcao on 2018/3/12.
 */
@Controller
@RequestMapping("/v/0.1/users")
public class UserController {

    @RequestMapping(value = "/authentication", method = RequestMethod.GET)
    @ResponseBody
    public UserDTO getUser(HttpSession session){

        Authentication authentication = (Authentication) session.getAttribute("authentication");

        if(authentication == null){
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setName(authentication.getUserName());
        userDTO.setId(authentication.getUserId());
        userDTO.setMobile(authentication.getPhone());
        return userDTO;


    }
}
