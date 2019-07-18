package com.qz.service;

import com.qz.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
    boolean sendRegCode(String regEmail, HttpServletRequest req);

    boolean doRegCode(String regCode, HttpServletRequest req);

    boolean doqzUsername(String qzUsername);

    boolean doRegister(User user);

    boolean doLogin(User user, String remeberMe, HttpServletResponse resp);

    User doQueryUser(HttpServletRequest req);

    boolean doLoginout(HttpServletRequest req, HttpServletResponse resp);
}
