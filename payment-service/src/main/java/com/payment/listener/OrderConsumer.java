package com.payment.listener;


import com.order.model.Order;
import com.payment.service.PaymentService;
import org.slf4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(OrderConsumer.class);

    private final PaymentService paymentService;

    public OrderConsumer(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @KafkaListener(topics = "ORD_TOPIC", groupId = "payment-service-group")
    public void processOrder(Order order, Acknowledgment acknowledgment) {
        logger.info("Received order: {}", order);
        try {
            paymentService.savePayment(order);
        } catch (DataIntegrityViolationException e) { // Idempotency check for duplicate orders
            logger.error("Duplicate order detected for order id: {}. Skipping processing.", order.orderId());
            acknowledgment.acknowledge(); // Acknowledge the message to prevent reprocessing, or you can choose to not acknowledge to trigger retry
            return;
        }
        logger.info("In OrderConsumer, payment processed successfully for order: {}", order);

        // Simulate an exception to test retry mechanism
        if(true)
            throw new RuntimeException("Simulated exception for testing retry mechanism for Order id: " + order.orderId());
        // Acknowledge the message manually after successful processing
        acknowledgment.acknowledge();
    }
}
