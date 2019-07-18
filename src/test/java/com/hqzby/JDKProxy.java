package com.hqzby;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JDKProxy implements InvocationHandler {

    private Object object;

    public JDKProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("前");
        Object invoke = method.invoke(object, args);
        System.out.println("后");
        return invoke;
    }


    public static void main(String[] args) {


    }


}
