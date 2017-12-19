package com.lubanresearch.lubanmall.common.exception;

/**
 * Created by hilbertcao on 2017/12/16.
 */
public class CommandValidateException extends ServiceException {
    protected String desc = "请求不符合要求";
    protected Integer code = 210000;
    public CommandValidateException(){}
    public CommandValidateException(Integer code, String desc){super(code, desc);}
    public CommandValidateException(Integer code){super(code);}
}
