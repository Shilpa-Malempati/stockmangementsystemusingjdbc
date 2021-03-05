package com.jfsfeb.stockmanagementsystemwithjdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.AdminBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.CompanyManagerBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.StockBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.exceptions.StockException;
import com.jfsfeb.stockmanagementsystemwithjdbc.utility.JdbcUtilityForStock;



public class CompanyManagerDaoImpl implements CompanyManagerDao {

	JdbcUtilityForStock dataBaseConnector = new JdbcUtilityForStock();
	@Override
	public CompanyManagerBean managerLogin(String email, String password) {
		 CompanyManagerBean admin = new CompanyManagerBean();

			try (Connection connection = dataBaseConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(dataBaseConnector.getSQLQuery("companyMangerLogin"))){
				
			        preparedStatement.setString(1, email);
					preparedStatement.setString(2, password);
					ResultSet resultSet = preparedStatement.executeQuery();
					if (resultSet.next()) {
						CompanyManagerBean bean = new CompanyManagerBean();
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
	public boolean changePassword(long mobile, String password) {
		try (Connection connection = dataBaseConnector.getConnection();
				 PreparedStatement preparedStatement = connection.prepareStatement(dataBaseConnector.getSQLQuery("changePassword1"));) {
			    preparedStatement.setString(1, password);
				preparedStatement.setLong(2, mobile);
				
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
	public boolean addStock(StockBean stockBean) {
		try (Connection connection = dataBaseConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(dataBaseConnector.getSQLQuery("addStocks"))){
				
				preparedStatement.setInt(1, stockBean.getId());
				preparedStatement.setString(2, stockBean.getCompanyName());
				preparedStatement.setInt(3, stockBean.getNoOfProducts());
				preparedStatement.setInt(4, stockBean.getCost());
				preparedStatement.setString(5, stockBean.getTypeOfStock());
				
				int p=preparedStatement.executeUpdate();
				System.out.println(p);
				
			} catch (Exception e) {
				throw new StockException("stock already existed ,please add new stock");
			}
		
		return true;
	}

	@Override
	public boolean updateStockTypeById(int id, String type) {
		try (Connection connection = dataBaseConnector.getConnection();
				 PreparedStatement preparedStatement = connection.prepareStatement(dataBaseConnector.getSQLQuery("updateStockNameById"));) {
			    preparedStatement.setString(1, type);
				preparedStatement.setInt(2,id );
				
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
	public boolean updateStockCostById(int id, int cost) {
		try (Connection connection = dataBaseConnector.getConnection();
				 PreparedStatement preparedStatement = connection.prepareStatement(dataBaseConnector.getSQLQuery("updateStockCostById"));) {
			    preparedStatement.setInt(1,cost);
				preparedStatement.setInt(2,id );
				
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
	public boolean updateStockCountById(int id, int count) {
		try (Connection connection = dataBaseConnector.getConnection();
				 PreparedStatement preparedStatement = connection.prepareStatement(dataBaseConnector.getSQLQuery("updateStockCountById"));) {
			    preparedStatement.setInt(1,count);
				preparedStatement.setInt(2,id);
				
				int count1 = preparedStatement.executeUpdate();
				if (count1 != 0) {
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
	public boolean removeStock(int id) {
		try (Connection connection = dataBaseConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(dataBaseConnector.getSQLQuery("removeStock"));) {

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
	public List<StockBean> searchProductByName(String name) {
		
		try (Connection connection = dataBaseConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(dataBaseConnector.getSQLQuery("searchProductByName"));) {

			preparedStatement.setString(1, name);
			ResultSet resultSet= preparedStatement.executeQuery();
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
	public List<StockBean> searchProductByType(String type) {
		try (Connection connection = dataBaseConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(dataBaseConnector.getSQLQuery("searchProductByType"));) {

			preparedStatement.setString(1, type);
			ResultSet resultSet= preparedStatement.executeQuery();
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
	public List<StockBean> getAllStcokInfo() {
		try (Connection connection = dataBaseConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(dataBaseConnector.getSQLQuery("getAllStcokInfo"));) {
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

}
