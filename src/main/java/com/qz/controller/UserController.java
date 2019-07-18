package com.qz.controller;

import com.qz.pojo.User;
import com.qz.service.UserService;
import com.qz.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //登录与注册
    @RequestMapping("/doLogin")
    public SysResult doLogin(User user, String remeberMe, HttpServletResponse resp){

        System.out.println("user: " + user);
        System.out.println("remeberMe: " + remeberMe);

        return SysResult.out(userService.doLogin(user, remeberMe, resp));
    }

    @RequestMapping("/doRegister")
    public SysResult doRegister(User user){

        return SysResult.out(userService.doRegister(user));
    }

    //根据token实现免登陆
    @RequestMapping("/doQueryUser")
    public SysResult doQueryUser(HttpServletRequest req){
        User user = userService.doQueryUser(req);
        return SysResult.out(user != null, user);
    }
    //退出登录
    @RequestMapping("/doLoginout")
    public SysResult doLoginout(HttpServletRequest req, HttpServletResponse resp){

        return SysResult.out(userService.doLoginout(req, resp));
    }
    //忘记密码

    //注册验证///////////////////////
    //验证用户名是否已经被注册
    @RequestMapping("/doqzUsername")
    public SysResult doqzUsername(String qzUsername){

        return SysResult.out(userService.doqzUsername(qzUsername));
    }

    //发送验证码
    @RequestMapping("/doSendEmail")
    public SysResult doSendEmail(String regEmail, HttpServletRequest req){

        return SysResult.out(userService.sendRegCode(regEmail, req));
    }

    //验证验证码是否正确
    @RequestMapping("/doRegCode")
    public SysResult doRegCode(String regCode, HttpServletRequest req){

        return SysResult.out(userService.doRegCode(regCode, req));
    }

}
