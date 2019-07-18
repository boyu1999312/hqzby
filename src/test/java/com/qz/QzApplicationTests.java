package com.qz;

import com.qz.util.MailUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisCluster;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QzApplicationTests {

    @Autowired
    private JedisCluster jedisCluster;

    @Test
    public void contextLoads() {
        MailUtil mailUtil = new MailUtil("1062664142@qq.com", "715048");
        //Thread thread = new Thread(mailUtil);
        //thread.start();
    }

    @Test
    public void test01() {
        MailUtil mailUtil = new MailUtil("1062664142@qq.com", "715048");
        //mailUtil.run();
    }

    @Test
    public void test02(){
        String code = "" + (int)((Math.random()*9+1)*100000);
        System.out.println("code: " + code);
        //jedisCluster.set("testCode", code);
        jedisCluster.del("testCode");
        String testCode = jedisCluster.get("testCode");
        System.out.println("testCode: " + testCode);
    }

}
