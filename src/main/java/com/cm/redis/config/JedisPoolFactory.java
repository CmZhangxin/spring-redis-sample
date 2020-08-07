package com.cm.redis.config;

import com.ctg.itrdc.cache.pool.CtgJedisPool;
import com.ctg.itrdc.cache.vjedis.jedis.HostAndPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cmzhang
 */
@Configuration
public class JedisPoolFactory {

    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public CtgJedisPool getCtgJedisPool(){
        String nodes = redisProperties.getNodes();
        List<HostAndPort> hostAndPortList = new ArrayList();
        HostAndPort host1 = new HostAndPort("132.122.232.225" ,8352);
        HostAndPort host2 = new HostAndPort("132.122.232.225" ,8362);
        hostAndPortList.add(host1);
        hostAndPortList.add(host2);

    }

}
