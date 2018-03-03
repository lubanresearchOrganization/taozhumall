package com.lubanresearch.lubanmall.orderservice.domain;

/**
 * Created by hilbertcao on 2018/2/13.
 */
public enum OrderStatus {

    CREATED(1),CANCELED(2),PAID(3),DELIVERED(4), SUCCESS(5);
    private byte value;
    OrderStatus(int value){this.value = (byte) value;}

    public byte getValue(){
        return value;
    }
}
