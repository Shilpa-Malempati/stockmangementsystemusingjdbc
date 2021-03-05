package com.jfsfeb.stockmanagementsystemwithjdbc.dao;

import java.util.List;

import com.jfsfeb.stockmanagementsystemwithjdbc.dto.AdminBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.CompanyBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.CompanyManagerBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.StockBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.UserBean;

public interface AdminDao {
	boolean registerCompanyManager(CompanyManagerBean managerBean);
	AdminBean adminLogin(String email,String password);
	boolean removeManager(int id);
	boolean updateManager(String mail, long phNum);
	boolean addCompany(CompanyBean companyBean);
	boolean updateCompany(String comName,int id);
	boolean removeCompany(String compName);
	
	List<UserBean> showUsers();
	List<CompanyBean> getAllCompanies();
	List<StockBean> getAllStcokInfo();
	List<CompanyManagerBean> getAllCompanyManagerInfo();
	
	
	
}
