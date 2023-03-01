package com.example.inmemoryqueue.service;

import com.example.inmemoryqueue.model.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Consumer implements Subscriber {

    Logger log = LoggerFactory.getLogger(Consumer.class);
    private String id;
    private String topic;
    private int offset = 0;

    public Consumer(String id, String topic){
        this.topic = topic;
        this.id = id;
    }
    @Override
    public void consumerMessage(Message message) {
        if (message.getTopic().equals(topic) && message.getId() > offset){
            log.info("Consumed message for Topic: " + topic+ " with data "+ message.getData());
            offset = message.getId();
        }
    }
}
