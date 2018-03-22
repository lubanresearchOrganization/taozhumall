package com.lubanresearch.lubanmall.usercenter.infrastructure.remote;

import com.lubanmall.userserviceapi.bean.UserDTO;
import com.lubanresearch.lubanmall.common.exception.ServiceException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hilbertcao on 2018/3/20.
 */
@FeignClient(name = "userservice")
public interface UserService {

    @RequestMapping(value = "/v/0.1/users/authentication", method = RequestMethod.GET)
    @ResponseBody
    UserDTO getAuthentication(@RequestParam("name") String name, @RequestParam("password")String password) throws ServiceException;

    @RequestMapping(value = "/v/0.1/users/", method = RequestMethod.POST)
    @ResponseBody
    UserDTO createUser(UserDTO userDTO);
}