package com.lubanresearch.lubanmall.userservice.infrastructure.config;


import com.lubanresearch.lubanmall.common.bean.Response;
import com.lubanresearch.lubanmall.common.exception.ServiceException;
import org.axonframework.commandhandling.CommandExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class WebExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebExceptionHandler.class);


    @ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public Response serviceExceptionHandler(Exception e) {

        LOGGER.error(e.getMessage(), e);
        ServiceException serviceException = (ServiceException) e;
        return new Response(serviceException.getCode(),serviceException.getMessage(),null);
    }

    @ExceptionHandler(value = CommandExecutionException.class)
    @ResponseBody
    public Response commandExecutionExceptionHandler(HttpServletRequest req, HttpServletResponse resp, Exception e) {

        LOGGER.error(e.getMessage(), e);
        CommandExecutionException commandExecutionException = (CommandExecutionException) e;
        ServiceException serviceException = (ServiceException) e.getCause();
        return new Response(serviceException.getCode(),serviceException.getMessage(),null);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response nonServiceExceptionHandler(HttpServletRequest req, HttpServletResponse resp, Exception e) {

        LOGGER.error(e.getMessage(), e);
        return new Response(500, e.getMessage(),null);
    }

}

