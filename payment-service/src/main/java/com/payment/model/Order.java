package com.payment.model;


import java.math.BigDecimal;

public record Order(String orderId, String productId, int quantity, BigDecimal price) {
}
