package com.example.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

@RestController
public class HelloController {

    @Autowired
    private StreamProducerImpl streamProducer;

    @GetMapping("/hello/{count}")
    public Mono<Void> sayHello(@PathVariable(required = false) Integer count){
        count = Objects.isNull(count) ? 10 : count;
        return Flux.range(1, count)
                .map(u -> MessageDTO.builder().orgId("9441").userId(String.valueOf(Instant.now().toEpochMilli())).build())
                .delayElements(Duration.ofSeconds(0))
                .flatMap(r -> streamProducer.publishEvent(r))
                .doOnNext(System.out::println)
                .then();
    }


}
