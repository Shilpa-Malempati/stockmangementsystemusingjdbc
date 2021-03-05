package com.jfsfeb.stockmanagementsystemwithjdbc.dto;

import lombok.Data;


@Data
public class StockBean {
	 private int id;
	 private String companyName;
	 private int  noOfProducts;
	 private int  cost;
	 private String typeOfStock;
	 
	
}
