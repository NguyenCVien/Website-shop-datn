package com.websiteshop.model;

import java.io.Serializable;
import java.util.List;

import com.websiteshop.entity.Account;
import com.websiteshop.entity.OrderDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long orderId;
    private String createDay;
    private String telePhone;
    private String address;
    private String name;
    private String email;
    private Account account;
    private List<OrderDetail> orderDetails;
    private Boolean isEdit;
}