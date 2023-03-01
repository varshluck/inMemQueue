package com.example.inmemoryqueue.service;

import com.example.inmemoryqueue.core.InMemoryQueue;
import com.example.inmemoryqueue.model.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessagePublisher implements Publisher{

    private InMemoryQueue memoryQueue;
    @Override
    public void publishMessage(String topic, Object data) {
        memoryQueue.publishMessage(topic, data);
    }
}
