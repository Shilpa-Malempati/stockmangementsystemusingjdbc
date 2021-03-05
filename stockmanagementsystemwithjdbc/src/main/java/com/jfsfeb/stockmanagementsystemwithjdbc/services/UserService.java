package com.jfsfeb.stockmanagementsystemwithjdbc.services;

import java.util.List;

import com.jfsfeb.stockmanagementsystemwithjdbc.dto.BuyStockBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.StockBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.UserBean;


public interface UserService {
	boolean registerUser(UserBean user);
	UserBean loginUser(String email,String password);
	public boolean buyStock(UserBean user, StockBean stockBean);
	List<StockBean> searchProductByType(String type);
	List<StockBean> searchProductByName(String name);
	List<StockBean> getAllStcokInfo();
	List<BuyStockBean> getAllBuyStockInfo();
	boolean changePassword(long mobile, String password);
	boolean updateProfile(String mail, long phNum);


}
