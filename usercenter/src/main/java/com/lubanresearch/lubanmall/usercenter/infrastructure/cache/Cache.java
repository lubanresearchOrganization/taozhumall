package com.lubanresearch.lubanmall.usercenter.infrastructure.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hilbertcao on 2017/11/12.
 */
public class Cache {
    private static Map hashMap = new HashMap();
    public static void put(String key,Object value){

        hashMap.put(key,value);
    }

    public static <T> T get(String key){

        return (T)hashMap.get(key);
    }
}
