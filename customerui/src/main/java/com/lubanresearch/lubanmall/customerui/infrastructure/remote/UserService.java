package com.lubanresearch.lubanmall.customerui.infrastructure.remote;

import com.lubanmall.userserviceapi.bean.UserDTO;
import com.lubanmall.userserviceapi.command.ChangepasswordCommandDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hilbertcao on 2018/3/25.
 */
@FeignClient(name = "userservice")
public interface UserService {

    @RequestMapping(value = "/v/0.1/users/{id}/commands/changePassword",method = RequestMethod.POST)
    @ResponseBody
    UserDTO changePassword(@PathVariable("id")Long id, @RequestBody ChangepasswordCommandDTO command);

    @RequestMapping(value = "/v/0.1/users/{id}",method = RequestMethod.PUT)
    @ResponseBody
    UserDTO update(@PathVariable("id")Long id,@RequestBody UserDTO user);

}
