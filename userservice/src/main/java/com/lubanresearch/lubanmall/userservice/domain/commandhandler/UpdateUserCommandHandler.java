package com.lubanresearch.lubanmall.userservice.domain.commandhandler;

import com.lubanresearch.lubanmall.common.exception.ServiceException;
import com.lubanresearch.lubanmall.userservice.domain.User;
import com.lubanresearch.lubanmall.userservice.domain.UserRepository;
import com.lubanresearch.lubanmall.userservice.domain.command.UpdateUserCommand;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by hilbertcao on 2018/3/25.
 */
@Component
public class UpdateUserCommandHandler {


    @Autowired
    private UserRepository userRepository;

    @CommandHandler
    public User handle(UpdateUserCommand command) throws ServiceException {


        User user = userRepository.getById(command.getId());
        if (user == null) {
            throw new ServiceException(500, "更新的实体不存在");
        }

        if (command.getName() != null && !"".equals(command.getName().trim())) {
            //// 判断名字重复
            User existUser = userRepository.getByName(command.getName());
            if (existUser != null && existUser.getId() != command.getId()) {
                throw new ServiceException(500, "用户已经存在");
            }
            user.setName(command.getName());

        }

        if (command.getMobile() != null && !"".equals(command.getMobile().trim())) {
            user.setMobile(command.getMobile());
        }

        userRepository.update(user);
        return user;
    }

}
