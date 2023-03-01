package com.example.inmemoryqueue.service;

import com.example.inmemoryqueue.model.Message;

public interface Publisher {
    void publishMessage(String topic, Object data);
}
