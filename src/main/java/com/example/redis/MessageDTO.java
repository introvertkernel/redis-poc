package com.example.redis;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.Record;
import org.springframework.data.redis.connection.stream.RecordId;

import java.io.Serializable;

@Data
@Builder
public class MessageDTO {
    private String userId;
    private String orgId;
    private String groupId;

}
