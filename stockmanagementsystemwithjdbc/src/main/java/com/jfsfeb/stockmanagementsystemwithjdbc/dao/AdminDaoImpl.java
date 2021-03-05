package com.jfsfeb.stockmanagementsystemwithjdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.AdminBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.CompanyBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.CompanyManagerBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.StockBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.UserBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.exceptions.StockException;
import com.jfsfeb.stockmanagementsystemwithjdbc.utility.JdbcUtilityForStock;

public class AdminDaoImpl implements AdminDao{
	
	JdbcUtilityForStock dataBaseConnector = new JdbcUtilityForStock();
	@Override
	public boolean registerCompanyManager(CompanyManagerBean managerBean) {
				
		try (Connection connection = dataBaseConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(dataBaseConnector.getSQLQuery("companyManagerRegister"))){
				
				preparedStatement.setInt(1, managerBean.getId());
				preparedStatement.setString(2, managerBean.getName());
				preparedStatement.setLong(3, managerBean.getPhoneNumber());
				preparedStatement.setString(4, managerBean.getMailId());
				preparedStatement.setString(5,managerBean.getPassword());
				preparedStatement.setString(6, managerBean.getRole());

				int p=preparedStatement.executeUpdate();
				System.out.println(p);
				return true;
			} catch (Exception e) {
				throw new StockException("manager already registred ,please try to login");
			}
	}

	@Override
	public AdminBean adminLogin(String email, String password) {
		 AdminBean admin = new AdminBean();

			try (Connection connection = dataBaseConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(dataBaseConnector.getSQLQuery("adminLogin"))){
				
				
					preparedStatement.setString(1, email);
					preparedStatement.setString(2, password);
					ResultSet resultSet = preparedStatement.executeQuery();
					if (resultSet.next()) {
						AdminBean bean = new AdminBean();
						bean.setId(resultSet.getInt("id"));
						bean.setName(resultSet.getString("name"));
						bean.setPhoneNumber(resultSet.getLong("phoneNumber"));
						bean.setMailId(resultSet.getString("mailId"));
						bean.setPassword(resultSet.getString("password"));
						bean.setRole(resultSet.getString("role"));
						
						return bean;
					} else {
						return null;
					}

				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}

     }

	@Override
	public boolean removeManager(int id) {
		
		try (Connection connection = dataBaseConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(dataBaseConnector.getSQLQuery("removeManager"));) {

			preparedStatement.setInt(1, id);
			int count =preparedStatement.executeUpdate();
			if (count != 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	
	}

	@Override
	public boolean updateManager(String mail, long phNum) {
		try (Connection connection = dataBaseConnector.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(dataBaseConnector.getSQLQuery("updateManager"));) {

			preparedStatement.setString(1, mail);
			preparedStatement.setLong(2, phNum);
			int count = preparedStatement.executeUpdate();
			if (count != 0) {
				    return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}


	}

    @Override
	public boolean updateCompany(String comName, int id) {
		try (Connection connection = dataBaseConnector.getConnection();
				 PreparedStatement preparedStatement = connection.prepareStatement(dataBaseConnector.getSQLQuery("updateCompany"));) {
			      preparedStatement.setString(1, comName);
				preparedStatement.setInt(2, id);
				
				int count = preparedStatement.executeUpdate();
				if (count != 0) {
					    return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

	}

	@Override
	public List<UserBean> showUsers() {
		
		try (Connection connection = dataBaseConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(dataBaseConnector.getSQLQuery("getAllInvestors"));) {
			ResultSet resultSet = preparedStatement.executeQuery();
			List<UserBean> beans = new ArrayList<UserBean>();
			while (resultSet.next()) {
				UserBean bean = new UserBean();
				bean.setId(resultSet.getInt("id"));
				bean.setName(resultSet.getString("name"));
				bean.setPhoneNumber(resultSet.getLong("phoneNumber"));
				bean.setMailId(resultSet.getString("mailId"));
				bean.setPassword(resultSet.getString("password"));
				beans.add(bean);
			}
			return beans;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

   }

	@Override
	public List<CompanyBean> getAllCompanies() {
		try (Connection connection = dataBaseConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(dataBaseConnector.getSQLQuery("getAllCompanies"));) {
			ResultSet resultSet = preparedStatement.executeQuery();
			List<CompanyBean> beans = new ArrayList<CompanyBean>();
			while (resultSet.next()) {
				CompanyBean bean = new CompanyBean();
				bean.setCompanyId(resultSet.getInt("companyId"));
				bean.setCompName(resultSet.getString("compName"));
				beans.add(bean);
			}
			return beans;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<StockBean> getAllStcokInfo() {
		try (Connection connection = dataBaseConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(dataBaseConnector.getSQLQuery("getAllStock"));) {
			ResultSet resultSet = preparedStatement.executeQuery();
			List<StockBean> beans = new ArrayList<StockBean>();
			while (resultSet.next()) {
				StockBean bean = new StockBean();
				bean.setId(resultSet.getInt("id"));
				bean.setCompanyName(resultSet.getString("companyName"));
				bean.setNoOfProducts(resultSet.getInt("noOfProducts"));
				bean.setCost(resultSet.getInt("cost"));
				bean.setTypeOfStock(resultSet.getString("typeOfStock"));
				
				beans.add(bean);
			}
			return beans;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public List<CompanyManagerBean> getAllCompanyManagerInfo() {
		try (Connection connection = dataBaseConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(dataBaseConnector.getSQLQuery("getAllCompanyManager"));) {
			ResultSet resultSet = preparedStatement.executeQuery();
			List<CompanyManagerBean> beans = new ArrayList<CompanyManagerBean>();
			while (resultSet.next()) {
				CompanyManagerBean bean = new CompanyManagerBean();
				bean.setId(resultSet.getInt("id"));
				bean.setName(resultSet.getString("name"));
				bean.setPhoneNumber(resultSet.getLong("phoneNumber"));
				bean.setMailId(resultSet.getString("mailId"));
				bean.setPassword(resultSet.getString("password"));
				beans.add(bean);
			}
			return beans;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
		

	@Override
	public boolean addCompany(CompanyBean companyBean) {
		try (Connection connection = dataBaseConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(dataBaseConnector.getSQLQuery("addCompany"))){
				
				preparedStatement.setInt(1, companyBean.getCompanyId());
				preparedStatement.setString(2, companyBean.getCompName());
				int p=preparedStatement.executeUpdate();
				System.out.println(p);
				return true;
			} catch (Exception e) {
				throw new StockException("manager already registred ,please try to login");
			}
			
     }

	
	@Override
	public boolean removeCompany(String companyName) {
		try (Connection connection = dataBaseConnector.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(dataBaseConnector.getSQLQuery("removeCompany"));) {

			preparedStatement.setString(1, companyName);
			int count =preparedStatement.executeUpdate();
			if (count != 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	
	}

}
