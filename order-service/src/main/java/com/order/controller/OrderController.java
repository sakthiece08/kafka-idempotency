package com.order.controller;


import com.order.model.Order;
import com.order.service.OrderProcessingService;
import org.slf4j.Logger;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(OrderController.class);

    private final OrderProcessingService orderProcessingService;

    public OrderController(OrderProcessingService orderProcessingService) {
        this.orderProcessingService = orderProcessingService;
    }

    @PostMapping("/api/orders")
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        logger.info("Received request to create order");
        // Process the order asynchronously
        orderProcessingService.processOrder(order);
        return ResponseEntity.created(null).body("Order received and is being processed");
    }

}
