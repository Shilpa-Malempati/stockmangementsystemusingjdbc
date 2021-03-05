package com.jfsfeb.stockmanagementsystemwithjdbc.services;

import java.util.List;

import com.jfsfeb.stockmanagementsystemwithjdbc.controllers.InvestorController;
import com.jfsfeb.stockmanagementsystemwithjdbc.dao.UserDao;
import com.jfsfeb.stockmanagementsystemwithjdbc.dao.UserDaoImpl;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.BuyStockBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.StockBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.UserBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.exceptions.StockException;
import com.jfsfeb.stockmanagementsystemwithjdbc.factory.StockFactory;
import com.jfsfeb.stockmanagementsystemwithjdbc.validations.InputValidation;
import com.jfsfeb.stockmanagementsystemwithjdbc.validations.InputValidationsImpl;

import lombok.val;



public class UserServiceImpl implements UserService {
	
    InputValidation validation = StockFactory.getInstanceOfInvaInputValidation();
    
	private UserDao dao = StockFactory.getInstanceOfUserDao();

	@Override
	public boolean registerUser(UserBean user) {
       if(user != null){
    	   if(validation.validatedId(user.getId())) {
				if(validation.validatedName(user.getName())) {
					if(validation.validatedMobile(user.getPhoneNumber())) {
						if(validation.validatedEmail(user.getMailId())) {
							if(validation.validatedPassword(user.getPassword())) {
								return dao.registerUser(user);
							}
						}
					}
				}
			}
       }
	return false;
	}

	@Override
	public UserBean loginUser(String email, String password) {
		if(email!=null && password != null ) {
			if(validation.validatedEmail(email)) {
				if(validation.validatedPassword(password)) {
					return dao.loginUser(email, password);
				}			
			}
		}
		return null;
	}

	@Override
	public boolean buyStock(UserBean user, StockBean stockBean) {
		if(user != null && stockBean != null) {
			if(validation.validatedId(stockBean.getId())) {
				if(validation.validatedId(user.getId())) {
					return dao.buyStock(user,stockBean);
				}
			}
		}
		return false;
	}

	@Override
	public List<StockBean> searchProductByType(String type) {
		if(type != null) {
			if(validation.validatedName(type)) {
				return dao.searchProductByType(type);
			}
		}
		return null;
		
	}

	@Override
	public List<StockBean> searchProductByName(String name) {
		if(name != null) {
			if(validation.validatedName(name)) {
				return dao.searchProductByName( name);
			}
		}
		return null;
	}

	@Override
	public List<StockBean> getAllStcokInfo() {

		return dao.getAllStcokInfo();
	}

	@Override
	public boolean changePassword(long mobile, String password) {
		if(mobile!=0 && password != null) {
			if(validation.validatedMobile(mobile)) {
				if(validation.validatedPassword(password)) {
			         return dao.changePassword( mobile,  password);
				}
			}
		}
		return false;
	}

	@Override
	public boolean updateProfile(String mail, long phNum) {
	 if(mail!= null && phNum !=0) {
		 if(validation.validatedEmail(mail)) {
			 if(validation.validatedMobile(phNum)) {
		        return dao.updateProfile(mail, phNum);
			 }
		 }
	 }
	return false;
	}

	@Override
	public List<BuyStockBean> getAllBuyStockInfo() {
		
		return dao.getAllBuyStockInfo();
	}





}
