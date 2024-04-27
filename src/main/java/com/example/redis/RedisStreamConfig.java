package com.example.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnection;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.StreamReceiver;
import org.springframework.data.redis.stream.Subscription;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;

@Configuration
public class RedisStreamConfig {

    @Autowired
    private StreamListener<String, ObjectRecord<String, MessageDTO>> streamListener;

    @Bean
    public Subscription subscription(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        StreamMessageListenerContainer.StreamMessageListenerContainerOptions<String, ObjectRecord<String, MessageDTO>> listenerContainerOptions = StreamMessageListenerContainer
                .StreamMessageListenerContainerOptions
                .builder()
//                .batchSize(10)
                .pollTimeout(Duration.ofSeconds(1))
                .targetType(MessageDTO.class)
                .build();

        StreamMessageListenerContainer<String, ObjectRecord<String, MessageDTO>> listenerContainer = StreamMessageListenerContainer
                .create(redisConnectionFactory, listenerContainerOptions);

        Subscription subscription = listenerContainer.receive(
                Consumer.from("dequeue", InetAddress.getLocalHost()
                        .getHostName()),
                StreamOffset.create("dequeue", ReadOffset.lastConsumed()),
                streamListener);

        listenerContainer.start();
        return subscription;
    }

//    @Bean
//    public ReactiveRedisTemplate<String, MessageDTO> messageDTOReactiveRedisTemplate(ReactiveRedisConnectionFactory factory){
//        RedisSerializationContext serializationContext = RedisSerializationContext.newSerializationContext(new StringRedisSerializer())
//                .value(new Jackson2JsonRedisSerializer(MessageDTO.class))
//                .build();
//        return new ReactiveRedisTemplate<>(factory,serializationContext);
//    }
//
//    @Bean
//    public StreamReceiver streamReceiver(ReactiveRedisConnectionFactory factory){
//        StreamReceiver.StreamReceiverOptions<String, MapRecord<String, String, String>> options = StreamReceiver.StreamReceiverOptions.builder().pollTimeout(Duration.ofSeconds(1)).build();
//        return StreamReceiver.create(factory, options);
//    }
}