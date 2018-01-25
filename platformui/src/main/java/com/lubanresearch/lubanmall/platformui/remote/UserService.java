package com.lubanresearch.lubanmall.platformui.remote;

import com.lubanmall.userserviceapi.bean.UserDTO;
import com.lubanresearch.lubanmall.common.exception.ServiceException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "userservice")
public interface UserService {


    @RequestMapping(value = "/v/0.1/users/", method = RequestMethod.POST)
    UserDTO addUser(@RequestBody UserDTO dto) throws ServiceException;


    @RequestMapping(value = "/v/0.1/users/{id}", method = RequestMethod.GET)
    UserDTO getUser(@PathVariable("id") Long id) throws ServiceException;

}
