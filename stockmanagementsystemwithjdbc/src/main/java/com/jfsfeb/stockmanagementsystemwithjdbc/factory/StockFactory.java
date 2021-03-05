package com.jfsfeb.stockmanagementsystemwithjdbc.factory;

import com.jfsfeb.stockmanagementsystemwithjdbc.dao.AdminDao;
import com.jfsfeb.stockmanagementsystemwithjdbc.dao.AdminDaoImpl;
import com.jfsfeb.stockmanagementsystemwithjdbc.dao.CompanyManagerDao;
import com.jfsfeb.stockmanagementsystemwithjdbc.dao.CompanyManagerDaoImpl;
import com.jfsfeb.stockmanagementsystemwithjdbc.dao.UserDao;
import com.jfsfeb.stockmanagementsystemwithjdbc.dao.UserDaoImpl;
import com.jfsfeb.stockmanagementsystemwithjdbc.services.AdminService;
import com.jfsfeb.stockmanagementsystemwithjdbc.services.AdminServiceImpl;
import com.jfsfeb.stockmanagementsystemwithjdbc.services.CompanyManagerService;
import com.jfsfeb.stockmanagementsystemwithjdbc.services.CompanyManagerServiceImpl;
import com.jfsfeb.stockmanagementsystemwithjdbc.services.UserService;
import com.jfsfeb.stockmanagementsystemwithjdbc.services.UserServiceImpl;
import com.jfsfeb.stockmanagementsystemwithjdbc.validations.InputValidation;
import com.jfsfeb.stockmanagementsystemwithjdbc.validations.InputValidationsImpl;

public class StockFactory {
	public static AdminDao getInstanceOfAdminDao() {
		return new AdminDaoImpl();
	}
	
	public static CompanyManagerDao getInstanceOfComapnyMangerDao() {
		return new CompanyManagerDaoImpl();
	}
	
	public static UserDao getInstanceOfUserDao() {
		return new UserDaoImpl();
	}
	public static AdminService getInstanceOfAdminService() {
		return new AdminServiceImpl();
	}
	
	public static UserService getInstanceOfUserService() {
		return new UserServiceImpl();
	}
	
	public static  CompanyManagerService getInstanceOfCompanyMangerService() {
		return new CompanyManagerServiceImpl();
	}
	
	public static  InputValidation getInstanceOfInvaInputValidation() {
		return new InputValidationsImpl();
	}
   

}
