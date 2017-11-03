package com.lubanresearch.lubanmall.common.exception;


/**
 * @author hilbert.cao
 * 异常基类
 */
public abstract class BaseException extends RuntimeException {

    /**
     * 结果代码response body中的code和message字段
     */
    protected Integer respCode;

    /**
     * 结果代码response body中的data字段
     */
    private String desc;

    public BaseException() {
    }

    public BaseException(Integer respCode, String desc) {
        super(desc);
        this.respCode = respCode;
        this.desc = desc;
    }



    public BaseException(Integer respCode, String desc, Throwable cause) {
        super(desc, cause);
        this.respCode = respCode;
        this.desc = desc;
    }


    /**
     * Gets 结果代码response body中的code和message字段.
     *
     * @return Value of 结果代码response body中的code和message字段.
     */
    public Integer getCode() {
        return respCode;
    }


    @Override
    public String getMessage() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(desc);
        return stringBuilder.toString();
    }


}
