package com.qz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

@Configuration
@PropertySource("classpath:/properties/redis.properties")
public class redisConfig {

    @Value("${redis.nodes}")
    private String nodes;

    @Bean
    public JedisCluster jedisCluster(){
        String[] node = nodes.split(",");
        Set<HostAndPort> set = new HashSet();
        for (String temp : node) {
            String[] ipPort = temp.split(":");
            set.add(new HostAndPort(ipPort[0],Integer.valueOf(ipPort[1])));
        }
        return new JedisCluster(set);
    }
}
