package com.jfsfeb.stockmanagementsystemwithjdbc.dto;

import lombok.Data;

@Data
public class UserBean {
	 private int id;
	 private String name;
	 private long phoneNumber;
	 private String mailId;
	 private String password;
	 private String role;
	
}
