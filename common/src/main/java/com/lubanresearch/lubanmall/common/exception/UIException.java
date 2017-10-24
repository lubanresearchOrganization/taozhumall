package com.lubanresearch.lubanmall.common.exception;

/**
 * Created by hilbert on 2017/10/24.
 */
public class UIException extends BaseException {

    public UIException() {
        //提供以反序列化
    }

    public UIException(Integer respCode) {
        super(respCode,"应用服务器异常");
    }

    public UIException(Integer respCode, String desc) {
        super(respCode, desc);
    }

    public UIException(Integer respCode, Throwable cause) {
        super(respCode, "应用服务器异常",cause);
    }

    public UIException(Integer respCode, String desc, Throwable cause) {
        super(respCode, desc, cause);
    }
}