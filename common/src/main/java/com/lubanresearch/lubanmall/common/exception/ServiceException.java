package com.lubanresearch.lubanmall.common.exception;



/**
 * @author hilbert.cao
 * 应用异常类
 */
public class ServiceException extends BaseException {

    protected String desc = "api 异常";
    protected Integer code = 200000;

    public ServiceException(){}
    public ServiceException(Integer code, String desc){super(code, desc);}
    public ServiceException(Integer code){super(code);}
}
