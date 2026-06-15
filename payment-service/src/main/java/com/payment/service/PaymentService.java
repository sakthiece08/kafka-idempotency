package com.payment.service;

import com.payment.entity.OrderEntity;
import com.order.model.Order;
import com.payment.repository.OrderRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(PaymentService.class);
    private final OrderRepository orderRepository;

    public PaymentService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional(readOnly = false)
    public void savePayment(Order orderDTO) {
        logger.info("Saving payment for order: {}", orderDTO.orderId());
        OrderEntity entity = new OrderEntity(
                orderDTO.orderId(),
                orderDTO.productId(),
                orderDTO.quantity(),
               orderDTO.price()
        );
        orderRepository.save(entity);
        logger.info("Payment for order {} saved successfully", orderDTO.orderId());
    }
}
