package com.websiteshop.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.websiteshop.dao.OrderDAO;
import com.websiteshop.dao.OrderDetailDAO;
import com.websiteshop.entity.Order;
import com.websiteshop.entity.OrderDetail;
import com.websiteshop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDAO odao;
    
    @Autowired
    OrderDetailDAO ddao;

    @Override
    public Order create(JsonNode orderData) {
        ObjectMapper mapper = new ObjectMapper();
        
        Order order = mapper.convertValue(orderData, Order.class);
        odao.save(order);
        
        TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
        List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type)
                .stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
        ddao.saveAll(details);
        
        return order;
    }

    @Override
    public Order findById(Long id) {
        return odao.findById(id).get();
    }

    @Override
    public List<Order> findByUsername(String username) {
//        return odao.findByUsername(username);
    	return null;
    }

}
