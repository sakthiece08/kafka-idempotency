package com.order.service;

import com.order.model.Order;
import org.slf4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class OrderProcessingService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(OrderProcessingService.class);

    public OrderProcessingService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void processOrder(Order order) {
        logger.info("Processing order: {}", order.orderId());
        // Simulate order processing logic
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("ORD_TOPIC", order.orderId(), order);
        future.whenComplete((result, ex) -> {
            if (ex == null)
                logger.info("Sent message=[{}] with offset=[{}] partition {}",
                        order.toString(), result.getRecordMetadata().offset(), result.getRecordMetadata().partition());
            else
                logger.error("Unable to send message=[{}] due to {} ", order.toString(), ex.getMessage());
        });
        logger.info("Order {} sent to Kafka topic ORD_TOPIC", order.orderId());
    }


}
