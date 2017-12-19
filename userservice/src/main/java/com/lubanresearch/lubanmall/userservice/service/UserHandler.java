package com.lubanresearch.lubanmall.userservice.service;

import com.lubanresearch.lubanmall.common.exception.ServiceException;
import com.lubanresearch.lubanmall.userservice.domain.command.RegisterCommand;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.springframework.stereotype.Component;

/**
 * Created by caopf on 2017/12/19.
 */
@Component
public class UserHandler {


    @CommandHandler
    public Long handle(RegisterCommand command)throws ServiceException {

        System.out.println(command.getName());
        throw  new ServiceException(600,"样例错误");
    }


}
