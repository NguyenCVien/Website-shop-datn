
package com.websiteshop.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Statitic {

    private Order order;
    private OrderDetail OrderDetail;
}
