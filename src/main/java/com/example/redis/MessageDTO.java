package com.example.redis;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.redis.connection.stream.Record;
import org.springframework.data.redis.connection.stream.RecordId;

@Data
@Builder
public class MessageDTO implements Record<String, MessageDTO> {
    private String userId;
    private String orgId;
    private String groupId;

    @Override
    public String getStream() {
        return this.getClass().getName();
    }

    @Override
    public RecordId getId() {
        return null;
    }

    @Override
    public MessageDTO getValue() {
        return null;
    }

    @Override
    public Record<String, MessageDTO> withId(RecordId id) {
        return null;
    }

    @Override
    public <SK> Record<SK, MessageDTO> withStreamKey(SK key) {
        return null;
    }
}
