package com.example.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class StreamProducerImpl {

    @Autowired
    @Qualifier("messageDTOReactiveRedisTemplate")
    private ReactiveRedisTemplate<String, MessageDTO> messageDTOReactiveRedisTemplate;

    public Mono<RecordId> publishEvent(MessageDTO messageDTO){
        ObjectRecord<String, MessageDTO> record = StreamRecords.newRecord()
                .ofObject(messageDTO)
                .withStreamKey("dequeue");

        return this.messageDTOReactiveRedisTemplate.opsForStream()
                .add(record);
    }

}
