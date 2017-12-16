package com.lubanmall.userserviceapi.bean;

/**
 * Created by hilbertcao on 2017/12/16.
 */
public enum UserType {
    SHOP((byte)2),CUSTOMER((byte)1),PLATFORM((byte)3);
    private byte value;
    UserType(byte value){
        this.value = value;
    }

    public byte getValue() {
        return value;
    }

    public UserType of(Byte value){
        if(value == null){

            return null;
        }
        for(UserType userType:values()){

            if(userType.getValue() == value.byteValue()){

                return userType;
            }
        }
        return null;
    }
}
