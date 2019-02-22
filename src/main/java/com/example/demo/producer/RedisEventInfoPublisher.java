package com.example.demo.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;


public class RedisEventInfoPublisher implements EventInfoPublisher {


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ChannelTopic topic;

    public RedisEventInfoPublisher() {
    }

    public RedisEventInfoPublisher(RedisTemplate<String, Object> redisTemplate, ChannelTopic topic) {
        this.redisTemplate = redisTemplate;
        this.topic = topic;
    }

    @Override
    public void publish(String message) {

        redisTemplate.convertAndSend(topic.getTopic(),message);
    }

}