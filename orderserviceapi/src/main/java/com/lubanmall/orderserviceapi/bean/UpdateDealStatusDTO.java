package com.lubanmall.orderserviceapi.bean;


public class UpdateDealStatusDTO {

    public Long id;

    public Byte status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
