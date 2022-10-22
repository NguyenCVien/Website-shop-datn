package com.websiteshop.model;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer orderId;
    private Date createDay = new Date();
    private String address;
}