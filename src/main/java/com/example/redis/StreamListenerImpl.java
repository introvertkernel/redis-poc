package com.example.redis;

import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class StreamListenerImpl implements StreamListener<String, ObjectRecord<String, MessageDTO>> {

    private Mono<MessageDTO> process(Mono<MessageDTO> messageDTO){
        return messageDTO
                .delayElement(Duration.ofSeconds(new Random().nextInt() % 10))
                .doOnNext(d -> System.out.println("time: "+ LocalDateTime.now() +" "+d+ " t:"+Thread.currentThread().getName()));
    }

    @Override
    public void onMessage(ObjectRecord<String, MessageDTO> message) {
        System.out.println("time: "+ LocalDateTime.now() +" "+message.getId()+ " t:"+Thread.currentThread().getName());
//        System.out.println();
        process(Mono.just(message.getValue())).subscribe();
    }
}
