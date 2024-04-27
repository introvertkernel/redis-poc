package com.example.redis;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.ReactiveRedisConnection;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.core.ReactiveRedisCallback;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.stream.StreamReceiver;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class StreamReceiverImpl  {

//    @Autowired
//    ReactiveRedisTemplate<String, MessageDTO> messageDTOReactiveRedisTemplate;
//
//
//    @Override
//    public Publisher doInRedis(ReactiveRedisConnection connection) throws DataAccessException {
//        return null;
//    }
}
