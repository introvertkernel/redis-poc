package com.example.redis;

import net.javacrumbs.shedlock.core.LockProvider;
import org.redisson.RedissonReactive;
import org.redisson.api.RLockReactive;
import org.redisson.api.RedissonReactiveClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;

@Configuration
public class RedisLockConfig {

    //https://github.com/redisson/redisson/wiki/8.-Distributed-locks-and-synchronizers

    //https://github.com/lukas-krecan/ShedLock?tab=readme-ov-file#redis-using-spring-reactiveredisconnectionfactory

//    @Bean(destroyMethod = "destroy")
//    public RedisLockRegistry redisLockRegistry(ReactiveRedisConnectionFactory factory){
//        return new RedisLockRegistry(factory.get, "test");
//    }

//    @Bean
//    RLockReactive rLockReactive(RedissonReactiveClient redissonReactiveClient){
//        return redissonReactiveClient.getLock("test");
//    }
//
//

//    @Bean
//    LockProvider lockProvider(ReactiveRedisConnectionFactory factory){
//        return new RedisLockConfig()
//    }

}
