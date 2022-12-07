package com.websiteshop.model;

import java.io.Serializable;

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
    private Account account;
    private String createDay;
    private String telePhone;
    private String address;
    private String name;
    private String email;
    private OrderDetail odt;
    private Boolean isEdit;
}