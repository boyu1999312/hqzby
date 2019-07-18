package com.qz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qz.mapper.UserMapper;
import com.qz.pojo.User;
import com.qz.service.UserService;
import com.qz.util.CusAccessObjectUtil;
import com.qz.util.JsonMapper;
import com.qz.util.MailUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JedisCluster jedisCluster;

    //用户登录与注册//////////////////////////////////
    //用户注册
    @Transactional
    @Override
    public boolean doRegister(User user) {
        user.setCreated(new Date()).setUpdated(user.getCreated());
        //对密码进行md5加密
        String md5Password = DigestUtils.md5DigestAsHex(user.getQzPassword().getBytes());
        user.setQzPassword(md5Password);
        //生成用户ID
        String id = UUID.randomUUID().toString().replace("-", "");
        user.setQzId(id);
        //执行数据库操作
        int insert = userMapper.insert(user);
        return insert == 1;
    }

    /**
     * 登录
     * 默认保存cookie3个小时
     * 勾选记住我保存30天
     * @param user 用户信息
     * @param remeberMe 是否记住我 null->false
     * @param resp 发送cookie
     * @return true表示执行成功
     */
    @Override
    public boolean doLogin(User user, String remeberMe, HttpServletResponse resp) {
        if(StringUtils.isEmpty(user.getQzUsername()) || StringUtils.isEmpty(user.getQzPassword()))
            return false;
        //对密码进行加密
        String md5Password = DigestUtils.md5DigestAsHex(user.getQzPassword().getBytes());
        user.setQzPassword(md5Password);
        User userDB = userMapper.selectOne(new QueryWrapper<User>(user));
        //账户密码正确
        if(userDB != null){
            //生成token  根据 ticket+username+当前毫秒值
            String token = "XZZJ_TICKET" + user.getQzUsername()
                    + System.currentTimeMillis();
            token = DigestUtils.md5DigestAsHex(token.getBytes());
            //设置时间 秒
            int time = remeberMe != null ? 3600 * 24 * 30 : 3600 * 3;
            //生成cookie
            Cookie cookie = new Cookie("XZZJ_TICKET", token);
            cookie.setMaxAge(time);
            cookie.setPath("/");
            resp.addCookie(cookie);
            jedisCluster.setex(token, time,
                    JsonMapper.toJson(new User().setQzUsername(user.getQzUsername())));
        }
        return true;
    }

    /**
     * 免登陆
     * @param req
     * @return user对象
     */
    @Override
    public User doQueryUser(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        //至少有一个cookie
        if(cookies == null || cookies.length == 0)
            return null;
        String json = null;
        //遍历
        for (Cookie cookie : cookies) {
            if("XZZJ_TICKET".equals(cookie.getName())) {
                json = jedisCluster.get(cookie.getValue());
                break;
            }
        }
        return JsonMapper.toData(json, User.class);
    }

    /**
     * 退出登录
     * @param resp
     * @return true 退出成功
     */
    @Override
    public boolean doLoginout(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        if(cookies.length == 0)
            return false;
        for (Cookie cookie : cookies) {
            if("XZZJ_TICKET".equals(cookie.getName())){
                jedisCluster.del(cookie.getValue());
                break;
            }
        }
        Cookie cookie = new Cookie("XZZJ_TICKET", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        resp.addCookie(cookie);
        return true;
    }


    //忘记密码//////////////////////////////////


    //用户信息校验//////////////////////////////////
    /**
     * 生成随机6位验证码 保存到redis时效2min 发送邮件给收件人
     * @param regEmail
     */
    @Override
    public boolean sendRegCode(String regEmail, HttpServletRequest req) {
        //生成验证码
        String code = "" + (int)((Math.random()*9+1)*100000);
        //获取客户端ip
        String ip = CusAccessObjectUtil.getIpAddress(req);
        //存入redis
        jedisCluster.setex(ip, 120, code);
        //发送邮件
        return MailUtil.send(regEmail, code);
    }

    /**
     * 校验验证码是否正确
     * @param regCode 验证码
     * @param req 用来获取用户ip
     * @return
     */
    @Override
    public boolean doRegCode(String regCode, HttpServletRequest req) {
        //获取客户端ip
        String ip = CusAccessObjectUtil.getIpAddress(req);
        String code = jedisCluster.get(ip);
        return regCode.equals(code);
    }

    /**
     *  验证用户名是否已被注册
     * @param qzUsername 用户名
     * @return
     */
    @Override
    public boolean doqzUsername(String qzUsername) {
        Integer count = userMapper.selectCount(new QueryWrapper<User>()
                .eq("qz_username", qzUsername));
        return count == 0;
    }

}
