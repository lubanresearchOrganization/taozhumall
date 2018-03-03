package com.lubanresearch.lubanmall.merchantservice.infrastructure.remote;

import com.lubanmall.userserviceapi.bean.UserDTO;
import com.lubanresearch.lubanmall.common.exception.ServiceException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * Created by hilbertcao on 2018/1/30.
 */
@FeignClient(name = "userservice",url = "http://userservice.taozhumall.com")
public interface UserService {

    @RequestMapping(value = "/v/0.1/users/", method = RequestMethod.GET)
    @ResponseBody
    List<UserDTO> getUsers(@RequestParam(value = "ids",required = false) List<Long> ids) throws ServiceException;
}