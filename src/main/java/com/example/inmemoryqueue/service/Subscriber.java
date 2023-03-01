package com.example.inmemoryqueue.service;

import com.example.inmemoryqueue.model.Message;

public interface Subscriber {
    void consumerMessage(Message message);
}
