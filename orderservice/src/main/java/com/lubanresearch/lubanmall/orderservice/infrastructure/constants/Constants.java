package com.lubanresearch.lubanmall.orderservice.infrastructure.constants;

public class Constants {


    //待发货
    public static final Byte WAIT_FOR_DELIVERY = (byte) 1;

    //已发货
    public static final Byte DELIVERED = (byte) 2;

    //待确认
    public static final Byte WAIT_FOR_CONFIRM = (byte) 3;

    //已完成
    public static final Byte FINISH = (byte) 4;


    //待付款
    public static final Byte OBLIGATION = (byte) 1;

    //已付款
    public static final Byte PAID = (byte) 2;

    //交易成功
    public static final Byte TRADE_SUCCESS = (byte) 3;

    //交易失败
    public static final Byte TRADE_FAIL = (byte) 4;

}
