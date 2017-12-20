package com.lubanresearch.lubanmall.userservice.domain.commandhandler;

import com.lubanresearch.lubanmall.common.exception.ServiceException;
import com.lubanresearch.lubanmall.userservice.domain.User;
import com.lubanresearch.lubanmall.userservice.domain.UserRepository;
import com.lubanresearch.lubanmall.userservice.domain.command.AddUserCommand;
import com.lubanresearch.lubanmall.userservice.infrastructure.util.MD5Util;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by hilbertcao on 2017/12/20.
 */
@Component
public class AddUserCommandHandler {

    @Autowired
    private UserRepository userRepository;

    @CommandHandler
    public User handle(AddUserCommand command)throws ServiceException {

        //// TODO: 2017/12/20 判断名字重复
        User existUser = userRepository.getByName(command.getName());
        if(existUser!=null){
            throw new ServiceException(500,"用户已经存在");
        }

        User user = new User();
        user.setMobile(command.getMobile());
        user.setName(command.getName());
        user.setPassword(MD5Util.encode(command.getPassword()));
        user.setType(command.getType());
        return userRepository.addUser(user);
    }

}
