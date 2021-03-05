package com.jfsfeb.stockmanagementsystemwithjdbc.dao;

import java.util.List;

import com.jfsfeb.stockmanagementsystemwithjdbc.dto.BuyStockBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.StockBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.UserBean;


public interface UserDao {
	boolean registerUser(UserBean user);
	UserBean loginUser(String email,String password);
	boolean buyStock(UserBean user, StockBean stockBean);
	List<StockBean> searchProductByType(String type);
	List<StockBean> searchProductByName(String name);
	List<StockBean> getAllStcokInfo();
	boolean changePassword(long mobile, String password);
	boolean updateProfile(String mail, long phNum);
	List<BuyStockBean> getAllBuyStockInfo();


}
