package com.websiteshop.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedBacksDto  implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer FeedBackId;
	private String discription;
}
