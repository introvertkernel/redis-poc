package com.example.redis;

import io.lettuce.core.api.reactive.RedisStreamReactiveCommands;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.stream.StreamReceiver;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;

@Service
public class StreamReceiverImpl  {

    @Autowired
    StreamReceiver<String, ObjectRecord<String, MessageDTO>> streamReceiver;

//    @Autowired
//    private RedisStreamReactiveCommands redisStreamReactiveCommands;

    private Disposable subscription;
//
//
    @PostConstruct
    public void subscribe(){
//      https://github.com/ecostanzi/k8slab/blob/b10ebc30600bcd5c76d27a8c88f89a39a755c1cf/product/src/main/java/org/ecostanzi/product/interfaces/OrderConsumer.java

        subscription = streamReceiver.receive(StreamOffset.latest("dequeue"))
                .doOnNext(System.out::println)
                .map(r -> r.getValue())
                .subscribe();
    }

    @PreDestroy
    private void destroy(){
        subscription.dispose();
    }
}
