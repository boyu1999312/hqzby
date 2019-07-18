package com.hqzby;

import com.alibaba.druid.sql.visitor.functions.Char;
import org.springframework.util.StringUtils;

public class SingLeTon {
    private static volatile SingLeTon instance = null;
    private SingLeTon(){}

    public static SingLeTon getInstance(){
        if(instance == null){
            synchronized (SingLeTon.class) {
                if (instance == null) instance = new SingLeTon();
            }
        }
        return instance;
    }


    public static void main(String[] args) {
        SingLeTon singLeTon = new SingLeTon();
        System.out.println( singLeTon.toBinary(8));
        System.out.println(singLeTon.toStr("我ABC汉CDE", 6));

    }

    public String toBinary(int num){
        String str = "";
        while(num != 0){
            str = num % 2 + str;
            num /= 2;
        }
        return str;
    }

    public String toStr(String str, int size){
        StringBuffer buffer = new StringBuffer();
        Character c = null;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if(!c.toString().matches("[\u4e00-\u9fa5]")){
                buffer.append(c);
                size--;
            }else if(size < 2){
                return buffer.toString();
            }else {
                buffer.append(c);
                size -= 2;
            }
            if(size == 0)
                return buffer.toString();
        }
        return buffer.toString();
    }
}
