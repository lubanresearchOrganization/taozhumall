package com.lubanresearch.lubanmall.common.exception;


/**
 * @author hilbert.cao
 * 异常基类
 */
public abstract class BaseException extends RuntimeException {

    /**
     * 结果代码response body中的code和message字段
     */
    protected Integer code;

    /**
     * 结果代码response body中的data字段
     */
    protected String desc;

    public BaseException() {
    }
    public BaseException(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public BaseException(Integer code) {
        this.code = code;
    }


    /**
     * Gets 结果代码response body中的code和message字段.
     *
     * @return Value of 结果代码response body中的code和message字段.
     */
    public Integer getCode() {
        return code;
    }


    @Override
    public String getMessage() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(desc);
        return stringBuilder.toString();
    }


}
