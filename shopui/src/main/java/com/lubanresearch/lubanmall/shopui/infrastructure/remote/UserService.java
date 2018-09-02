package com.lubanresearch.lubanmall.shopui.infrastructure.remote;

import com.lubanmall.userserviceapi.bean.UserDTO;
import com.lubanmall.userserviceapi.command.ChangepasswordCommandDTO;
import com.lubanresearch.lubanmall.common.exception.ServiceException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hilbertcao on 2018/2/5.
 */
@FeignClient(name = "userservice")
public interface UserService {

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    @ResponseBody
    void updateUser(@RequestBody UserDTO user) throws ServiceException;

    @RequestMapping(value = "/{id}/commands/changePassword",method = RequestMethod.POST)
    @ResponseBody
    UserDTO changePassword(@PathVariable("id")Long id, @RequestBody ChangepasswordCommandDTO command) throws ServiceException;

}
