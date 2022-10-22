package com.websiteshop.model;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto  implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer productId;
	private String name;
	private Integer quantity;
	private Float unitPrice;
	private Double discount;
	private String image1;
	private String image2;
	private String image3;
	private String image4;
	private String image5;
	private String Discription;
	private Date enteredDay = new Date();
}
