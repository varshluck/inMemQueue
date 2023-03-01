package com.example.inmemoryqueue.service;

import com.example.inmemoryqueue.model.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ConsumerGroup implements Subscriber {

    private String name;
    private List<Consumer> consumers = new ArrayList<>();
    @Override
    public void consumerMessage(Message message) {
        for (Consumer consumer: consumers){
            consumer.consumerMessage(message);
        }
    }
}
