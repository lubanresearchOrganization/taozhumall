package com.lubanresearch.lubanmall.platformui.infrastructure.remote;

import com.lubanmall.userserviceapi.bean.UserDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by hilbertcao on 2017/12/19.
 */
@FeignClient(name = "userservice")
public interface UserService {



    @RequestMapping(value = "/v/0.1/users/",method = RequestMethod.POST)
    public UserDTO addUser(@RequestBody UserDTO user);
}
