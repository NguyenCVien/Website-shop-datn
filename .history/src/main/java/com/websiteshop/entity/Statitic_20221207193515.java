
package com.websiteshop.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Statitic {

    @ManyToOne
    @JoinColumn(name = "Order")
    Order order;
    @ManyToOne
    @JoinColumn(name = "OrderDetailId")
    OrderDetail OrderDetail;
}
