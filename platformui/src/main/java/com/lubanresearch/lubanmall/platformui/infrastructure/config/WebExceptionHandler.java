package com.lubanresearch.lubanmall.platformui.infrastructure.config;


import com.lubanresearch.lubanmall.common.bean.Response;
import com.lubanresearch.lubanmall.common.exception.ServiceException;
import com.lubanresearch.lubanmall.common.exception.UIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class WebExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebExceptionHandler.class);

    @ExceptionHandler(value = UIException.class)
    @ResponseStatus(code= HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Response uiExceptionHandler(Exception e) {

        LOGGER.error(e.getMessage(), e);
        UIException uiException = (UIException) e;
        return new Response(uiException.getCode(),uiException.getMessage(),null);
    }

    @ExceptionHandler(value = ServiceException.class)
    @ResponseStatus(code= HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Response serviceExceptionHandler(Exception e) {

        LOGGER.error(e.getMessage(), e);
        ServiceException serviceException = (ServiceException) e;
        return new Response(serviceException.getCode(),serviceException.getMessage(),null);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(code= HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Response nonServiceExceptionHandler(HttpServletRequest req, HttpServletResponse resp, Exception e) {

        LOGGER.error(e.getMessage(), e);
        return new Response(500, e.getMessage(),null);
    }

}

