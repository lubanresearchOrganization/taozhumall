package com.lubanresearch.lubanmall.common.exception;



/**
 * @author hilbert.cao
 * 应用异常类
 */
public class ServiceException extends BaseException {

    public ServiceException() {
        //提供以反序列化
    }

    public ServiceException(Integer respCode) {
        super(respCode,"api异常");
    }

    public ServiceException(Integer respCode, String desc) {
        super(respCode, desc);
    }

    public ServiceException(Integer respCode, Throwable cause) {
        super(respCode, "api异常",cause);
    }

    public ServiceException(Integer respCode, String desc, Throwable cause) {
        super(respCode, desc, cause);
    }
}
