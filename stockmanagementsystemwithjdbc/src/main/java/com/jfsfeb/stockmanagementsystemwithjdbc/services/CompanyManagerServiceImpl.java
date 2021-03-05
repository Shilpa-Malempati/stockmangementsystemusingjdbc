package com.jfsfeb.stockmanagementsystemwithjdbc.services;

import java.util.List;

import com.jfsfeb.stockmanagementsystemwithjdbc.controllers.CompanyManagerController;
import com.jfsfeb.stockmanagementsystemwithjdbc.dao.CompanyManagerDao;
import com.jfsfeb.stockmanagementsystemwithjdbc.dao.CompanyManagerDaoImpl;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.AdminBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.CompanyManagerBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.StockBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.exceptions.StockException;
import com.jfsfeb.stockmanagementsystemwithjdbc.factory.StockFactory;
import com.jfsfeb.stockmanagementsystemwithjdbc.validations.InputValidation;
import com.jfsfeb.stockmanagementsystemwithjdbc.validations.InputValidationsImpl;

import lombok.val;

public class CompanyManagerServiceImpl implements CompanyManagerService {

	CompanyManagerDao dao = StockFactory.getInstanceOfComapnyMangerDao();
	
	InputValidation validation = StockFactory.getInstanceOfInvaInputValidation();
	
	@Override
	public CompanyManagerBean managerLogin(String email, String password) {
		if(email!=null && password !=null) {
			if(validation.validatedEmail(email)) {
				if(validation.validatedPassword(password)) {
					return dao.managerLogin(email, password);
				}
			}

		}
		return null;
	}

	@Override
	public boolean changePassword(long mobile, String password) {
		if(mobile !=0 && password!=null) {
			if(validation.validatedMobile(mobile)) {
				if(validation.validatedPassword(password)) {
					return dao.changePassword(mobile, password);
				}
			}
		}
		return false;
		
	}

	@Override
	public boolean addStock(StockBean stockBean) {
		if(stockBean != null) {
			if(validation.validatedId(stockBean.getId())) {
				if(validation.validatedName(stockBean.getTypeOfStock())) {
					if(validation.validatedName(stockBean.getCompanyName())) {
						if(validation.validatedId(stockBean.getNoOfProducts())) {
			                return dao.addStock(stockBean);
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean updateStockTypeById(int id, String type) {
		if(id!=0 && type != null) {
			if(validation.validatedId(id)) {
				if(validation.validatedName(type)) {
					return dao.updateStockTypeById(id, type);
				}
			}
		}
		return false;
	}

	@Override
	public boolean updateStockCostById(int id, int cost) {
		if(id != 0 && cost !=0) {
			if(validation.validatedId(id)) {
				return dao.updateStockCostById(id, cost);
			}
		}
		return false;
	}

	@Override
	public boolean updateStockCountById(int id, int count) {
		if(id!= 0 && count !=0) {
			if(validation.validatedId(id)) {
				return dao.updateStockCountById(id, count);
			}
		}
	return false;
	
	}

	@Override
	public boolean removeStock(int id) {
		if(id !=0) {
			if(validation.validatedId(id)) {
			return dao.removeStock(id);
			}
		}
		return false;
	}

	@Override
	public List<StockBean> searchProductByName(String name) {
	    if(name != null) {
	    	if(validation.validatedName(name)) {
		           return dao.searchProductByName(name);
	    	}
	    }
		return null;
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
	public List<StockBean> getAllStcokInfo() {
		
		return dao.getAllStcokInfo();
	}

}
