package com.order.model;


public record Order(String orderId, String productId, int quantity, double price) {
}
