package com.jfsfeb.stockmanagementsystemwithjdbc.dto;
import lombok.Data;

@Data
public class BuyStockBean {
	
	int stockId;
	String CompanyName;
	int noOfProducts;
	double cost;
	String stockType;
	int userId;
	String userName;


}
