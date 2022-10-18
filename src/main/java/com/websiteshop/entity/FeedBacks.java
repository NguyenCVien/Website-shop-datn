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
@Entity @Table(name = "FeedBacks")
public class FeedBacks  implements Serializable{
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer FeedBackId;
	@ManyToOne
    @JoinColumn(name = "Username")
    Account account;
	@ManyToOne
    @JoinColumn(name = "ProductId")
    Product product;
	String discription;
}
