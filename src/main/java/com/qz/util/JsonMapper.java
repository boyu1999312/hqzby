package com.qz.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonMapper extends ObjectMapper {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转换成json串
     * @param obj 对象实例
     * @return json串
     */
    public static String toJson(Object obj){
        String json = null;
        try {
            json =  MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 将json串转换成对象
     * @param json json串
     * @param cla 对象类型
     * @param <T> 指定类型
     * @return 对象实例
     */
    public static <T>T toData(String json, Class<T> cla){
        T obj = null;
        try {
            obj = MAPPER.readValue(json, cla);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
