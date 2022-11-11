package com.websiteshop.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.websiteshop.entity.Order;

public interface OrderService {

    Order create(JsonNode orderData);

    Order findById(Long orderId);

    List<Order> findByUsername(String username);
}
