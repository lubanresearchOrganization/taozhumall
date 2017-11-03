package com.lubanresearch.lubanmall.common.bean;

import java.io.Serializable;

/**
 * hilbert.cao
 *
 * @param <T>
 */
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Integer code;

    protected String message;

    protected T data;


    public Response() {
    }

    public Response(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
