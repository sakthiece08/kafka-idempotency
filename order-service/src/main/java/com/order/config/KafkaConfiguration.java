package com.order.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfiguration {

    @Bean
    public NewTopic createOrdersTopic() {
        return new NewTopic("ORD_TOPIC", 3, (short) 1);
    }
}
