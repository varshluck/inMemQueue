package com.example.inmemoryqueue;

import com.example.inmemoryqueue.core.Queue;
import com.example.inmemoryqueue.service.Consumer;
import com.example.inmemoryqueue.service.MessagePublisher;
import com.example.inmemoryqueue.service.Publisher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
*anshul.katare@dream11.com
* */
@SpringBootApplication
public class InMemoryQueueApplication {

    public static void main(String[] args) {
        SpringApplication.run(InMemoryQueueApplication.class, args);

        Queue queue = new Queue();
        queue.createTopic("topic1");
        queue.createTopic("topic2");

        // Create publishers
        Publisher publisher1 = new MessagePublisher(queue);
        Publisher publisher2 = new MessagePublisher(queue);

        // Create consumers
        Consumer consumer1 = new Consumer("consumer1", "topic1");
        Consumer consumer2 = new Consumer("consumer2", "topic1");
        Consumer consumer3 = new Consumer("consumer3", "topic2");

        // Subscribe consumers to topics
        queue.subscribeConsumer(consumer1);
        queue.subscribeConsumer(consumer2);
        queue.subscribeConsumer(consumer3);

        // Publish messages
        publisher1.publishMessage("topic1", "Message 1");
        publisher1.publishMessage("topic1", "Message 2");
        publisher2.publishMessage("topic2", "Message 3");

        // Wait for consumers to finish consuming messages
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print offsets
        System.out.println("Consumer 1 offset: " + consumer1.getOffset());
        System.out.println("Consumer 2 offset: " + consumer2.getOffset());
        System.out.println("Consumer 3 offset: " + consumer3.getOffset());

    }

}
