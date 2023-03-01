package com.example.inmemoryqueue.core;

import com.example.inmemoryqueue.model.Message;
import com.example.inmemoryqueue.service.Consumer;

public interface InMemoryQueue {

    void createTopic(String name);

    void subscribeConsumer(Consumer consumer);

    void subscribeConsumerGroups(String topicName, String groupName);

    void addConsumerToGroup(String topicName, String groupName, String consumerId);

    void publishMessage(String topic, Object data);
}
