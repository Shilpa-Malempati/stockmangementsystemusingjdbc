package com.jfsfeb.stockmanagementsystemwithjdbc.services;

import java.util.List;

import com.jfsfeb.stockmanagementsystemwithjdbc.dto.AdminBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.CompanyManagerBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.StockBean;

public interface CompanyManagerService {
	CompanyManagerBean managerLogin(String email,String password);
	boolean changePassword(long mobile,String password);
	boolean addStock(StockBean stockBean);
	boolean updateStockTypeById(int id,String type);
	boolean updateStockCostById(int id,int cost);
	boolean updateStockCountById(int id,int count);
	boolean removeStock(int id);
    List<StockBean> searchProductByName(String name);
	List<StockBean> searchProductByType(String type);
	List<StockBean> getAllStcokInfo();
}
