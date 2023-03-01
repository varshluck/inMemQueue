package com.example.inmemoryqueue.model;

import com.example.inmemoryqueue.service.Subscriber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Topic {

    private String name;
    private List<Subscriber> subscibers = new ArrayList<>();
    private List<Message> messages = new ArrayList<>();
}
