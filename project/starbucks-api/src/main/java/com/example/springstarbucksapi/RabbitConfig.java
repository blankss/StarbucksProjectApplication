package com.example.springstarbucksapi;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class RabbitConfig {
    @Bean
    Queue queue() {
        return new Queue("starbucks");
    }
}