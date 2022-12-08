package com.websiteshop.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Statitic")
public class Statitics implements Serializable {
	
	@Id
	private int id;

	@ManyToOne
	@JoinColumn(name = "OrderId")
	private Order order;

	@ManyToOne
	@JoinColumn(name = "OrderDetailId")
	private OrderDetail OrderDetail;

}
