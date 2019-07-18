package com.qz.local;

import com.qz.pojo.User;

public class UserLocal {
    private static final ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    public static void put(User user){
        userThreadLocal.set(user);
    }

    public static User get(){
        return userThreadLocal.get();
    }

    public static void remove(){
        userThreadLocal.remove();
    }
}
