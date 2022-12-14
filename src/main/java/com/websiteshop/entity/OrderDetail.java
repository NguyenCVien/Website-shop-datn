package com.websiteshop.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Orderdetails")
public class OrderDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer orderDetailId;
    @ManyToOne
    @JoinColumn(name = "OrderId")
    Order order;
    @ManyToOne
    @JoinColumn(name = "Productid")
    Product product;
    Double price;
    Double discount;
    String status;
    Integer quantity;
    String discription;

}
