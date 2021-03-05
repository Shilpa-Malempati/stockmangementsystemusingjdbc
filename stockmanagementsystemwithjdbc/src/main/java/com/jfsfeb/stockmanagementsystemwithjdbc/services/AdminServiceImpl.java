package com.jfsfeb.stockmanagementsystemwithjdbc.services;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.omg.CORBA.CTX_RESTRICT_SCOPE;

import com.jfsfeb.stockmanagementsystemwithjdbc.controllers.AdminController;
import com.jfsfeb.stockmanagementsystemwithjdbc.dao.AdminDao;
import com.jfsfeb.stockmanagementsystemwithjdbc.dao.AdminDaoImpl;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.AdminBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.CompanyBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.CompanyManagerBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.StockBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.UserBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.exceptions.StockException;
import com.jfsfeb.stockmanagementsystemwithjdbc.factory.StockFactory;
import com.jfsfeb.stockmanagementsystemwithjdbc.validations.InputValidation;
import com.jfsfeb.stockmanagementsystemwithjdbc.validations.InputValidationsImpl;

import lombok.val;

public class AdminServiceImpl implements AdminService{
	
	private AdminDao dao = StockFactory.getInstanceOfAdminDao();
	
	InputValidation validation = StockFactory.getInstanceOfInvaInputValidation();

	@Override
	public boolean registerCompanyManager(CompanyManagerBean managerBean) {
		if(managerBean!=null){
			if(validation.validatedId(managerBean.getId())) {
				if(validation.validatedName(managerBean.getName())) {
					if(validation.validatedMobile(managerBean.getPhoneNumber())) {
						if(validation.validatedEmail(managerBean.getMailId())) {
							if(validation.validatedPassword(managerBean.getPassword())) {
								return dao.registerCompanyManager(managerBean);
							}
						}
					}
				}
			}
		}
		return false;
	}
	@Override
	public AdminBean adminLogin(String email, String password) {
		if(email!=null && password !=null) {
			if (validation.validatedEmail(email)) {
				if (validation.validatedPassword(password)) {
					return dao.adminLogin(email, password);
				}
			}
		}
		return null;
	        
   }

	@Override
	public boolean removeManager(int id) {
		if(id!=0) {
			if(validation.validatedId(id)) {
			         return dao.removeManager(id);
			}
		}
		return false;
	}

	@Override
	public boolean updateManager(String mail, long phNum) {
       if(mail!=null && phNum!=0) {
    	   if(validation.validatedEmail(mail)) {
    		   if(validation.validatedMobile(phNum)) {
    	               return dao.updateManager(mail, phNum);
    		   }
    	   }
       }
	       return false;
	}
	
	@Override
	public boolean addCompany(CompanyBean companyBean) {
		if(companyBean !=null) {
			if(validation.validatedId(companyBean.getCompanyId())) {
				if(validation.validatedName(companyBean.getCompName())) {
			                return dao.addCompany(companyBean);
				}
			}
		}
		return false;
		
	}

	@Override
	public boolean removeCompany(String companyName) {
		if(companyName!=null) {
			if(validation.validatedName(companyName)) {
			return dao.removeCompany(companyName);
			}
		}
		return false;
	}

	@Override
	public boolean updateCompany(String comName, int id) {
		if(comName != null && id!=0) {
			if(validation.validatedName(comName)) {
				if(validation.validatedId(id)) {
			          return dao.updateCompany(comName, id);
				}
			}
		}
		return false;
	}

	@Override
	public List<UserBean> showUsers() {
		return dao.showUsers();
	}
	
    @Override
	public List<CompanyBean> getAllCompanies() {
		return dao.getAllCompanies();
	}

	@Override
	public List<StockBean> getAllStcokInfo() {
		return dao.getAllStcokInfo();
	}

	@Override
	public List<CompanyManagerBean> getAllCompanyManagerInfo() {
		 return dao.getAllCompanyManagerInfo();
	}

	
}

