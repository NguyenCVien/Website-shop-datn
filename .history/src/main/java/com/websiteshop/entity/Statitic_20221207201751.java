
package com.websiteshop.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Statitic {
    @Id
    private int id;
    private Order order;
    private OrderDetail OrderDetail;
}
