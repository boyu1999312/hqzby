package com.qz.controller;

import com.qz.pojo.User;
import com.qz.vo.SysResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/doLogin")
    public SysResult doLogin(User user){
        if("abc123".equals(user.getQzUsername()) && "asdf123".equals(user.getQzPassword())){
            return SysResult.ok();
        }
        return  SysResult.fail();
    }
}
