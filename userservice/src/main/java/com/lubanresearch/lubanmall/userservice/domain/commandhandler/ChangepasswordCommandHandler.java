package com.lubanresearch.lubanmall.userservice.domain.commandhandler;

import com.lubanresearch.lubanmall.common.exception.ServiceException;
import com.lubanresearch.lubanmall.userservice.domain.User;
import com.lubanresearch.lubanmall.userservice.domain.UserRepository;
import com.lubanresearch.lubanmall.userservice.domain.command.ChangepasswordCommand;
import com.lubanresearch.lubanmall.userservice.infrastructure.util.MD5Util;
import org.apache.commons.lang.StringUtils;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by hilbertcao on 2017/12/20.
 */
@Component
public class ChangepasswordCommandHandler {

    @Autowired
    private UserRepository userRepository;

    @CommandHandler
    public User handle(ChangepasswordCommand command)throws ServiceException {

        User user = userRepository.getById(command.getId());
        String encodeOldPassword = MD5Util.encode(command.getOldPassword());
        if(!user.getPassword().equals(encodeOldPassword)){

            throw new ServiceException(500,"旧密码不对，不能更新密码");
        }
        //密码相同没必要更新
        if(command.getOldPassword().equals(command.getNewPassword())){

            return user;
        }
        user.setPassword(MD5Util.encode(command.getNewPassword()));
        userRepository.update(user);
        return userRepository.getById(command.getId());
    }
}
