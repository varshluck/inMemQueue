package com.example.inmemoryqueue.core;

import com.example.inmemoryqueue.model.Message;
import com.example.inmemoryqueue.model.Topic;
import com.example.inmemoryqueue.service.Consumer;
import com.example.inmemoryqueue.service.ConsumerGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class Queue implements InMemoryQueue{

    Logger log  = LoggerFactory.getLogger(Queue.class);
    private Map<String, Topic> topics = new HashMap<>();
    private Map<String, Integer> topicMessageIds = new HashMap<>();


    @Override
    public void publishMessage(String topicName, Object data){
        Topic topic = topics.get(topicName);
        if (topic != null){
            int id = topicMessageIds.get(topicName) + 1;
            topicMessageIds.put(topicName, id);
            Message message = new Message(id, topicName, data);
            topic.getMessages().add(message);
            topic.getSubscibers().forEach(subscriber -> subscriber.consumerMessage(message));
        }
    }

    @Override
    public void createTopic(String name){
        //validate if topic already exists
        if(topics.get(name) != null){
            log.error("Topic already exists for name : "+ name);
        } else {
            topics.put(name, new Topic(name, new ArrayList<>(), new ArrayList<>()));
            topicMessageIds.put(name, 0);
        }
    }

    @Override
    public void subscribeConsumer(Consumer consumer){
        Topic topic = topics.get(consumer.getTopic());
        if (topic != null){
            topic.getSubscibers().add(consumer);
            topic.getMessages().forEach(message -> consumer.consumerMessage(message));
        }
    }

    @Override
    public  void subscribeConsumerGroups(String topicName, String groupName){
        Topic topic = topics.get(topicName);
        if (topic != null){
            ConsumerGroup group = new ConsumerGroup(groupName, new ArrayList<>());
            topic.getSubscibers().add(group);
        }
    }

    @Override
    public void addConsumerToGroup(String topicName, String groupName, String consumerId){
        Topic topic = topics.get(topicName);
        if (topic != null){
            ConsumerGroup group =(ConsumerGroup) topic.getSubscibers()
                    .stream()
                    .filter(subscriber -> subscriber instanceof ConsumerGroup)
                    .filter(subscriber -> ((ConsumerGroup) subscriber).getName().equals(groupName))
                    .findFirst()
                    .orElse(null);
            if(group != null){
                Consumer consumer = new Consumer(consumerId,topicName);
                group.getConsumers().add(consumer);
                topic.getMessages().forEach(message -> consumer.consumerMessage(message));
            }
        }
    }
}
