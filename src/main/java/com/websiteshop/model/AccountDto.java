package com.websiteshop.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String fullname;
	private String email;
	private String image;
	private String address;
	private String telePhone;
}
