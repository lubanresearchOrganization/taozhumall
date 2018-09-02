package com.lubanresearch.lubanmall.categoryservice.infrastructure.config;


import com.lubanresearch.lubanmall.common.bean.Response;
import com.lubanresearch.lubanmall.common.exception.ServiceException;
import org.apache.commons.lang.StringUtils;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.interceptors.JSR303ViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;

@ControllerAdvice
public class WebExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebExceptionHandler.class);


    @ExceptionHandler(value = ServiceException.class)
    @ResponseStatus(code= HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Response serviceExceptionHandler(Exception e) {

        LOGGER.error(e.getMessage(), e);
        ServiceException serviceException = (ServiceException) e;
        return new Response(serviceException.getCode(),serviceException.getMessage(),null);
    }

    @ExceptionHandler(value = CommandExecutionException.class)
    @ResponseStatus(code= HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Response commandExecutionExceptionHandler(HttpServletRequest req, HttpServletResponse resp, Exception e) {

        LOGGER.error(e.getMessage(), e);
        CommandExecutionException commandExecutionException = (CommandExecutionException) e;
        if(e.getCause() instanceof ServiceException){
            ServiceException serviceException = (ServiceException) e.getCause();
            new Response(serviceException.getCode(),serviceException.getMessage(),null);
        }
        if(e.getCause() instanceof JSR303ViolationException){
            JSR303ViolationException jsr303ViolationException = (JSR303ViolationException) e.getCause();
            String validateError = StringUtils.join(jsr303ViolationException.getViolations().stream().map(item->{return item.getPropertyPath()+item.getMessage();}).collect(Collectors.toList()),",");

            return new Response(500,validateError,null);
        }

        return new Response(500,e.getCause().getMessage(),null);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(code= HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Response nonServiceExceptionHandler(HttpServletRequest req, HttpServletResponse resp, Exception e) {

        LOGGER.error(e.getMessage(), e);
        return new Response(500, e.getMessage(),null);
    }

}

