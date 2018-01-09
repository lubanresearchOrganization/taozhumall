package com.lubanmall.orderserviceapi.bean;

import com.sun.istack.internal.NotNull;

public class UpdateDealStatusDTO {

    @NotNull
    public Long id;

    @NotNull
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
