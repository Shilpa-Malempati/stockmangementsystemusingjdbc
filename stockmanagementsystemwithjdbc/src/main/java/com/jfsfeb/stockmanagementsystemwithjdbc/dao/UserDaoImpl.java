package com.jfsfeb.stockmanagementsystemwithjdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.BuyStockBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.StockBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.UserBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.exceptions.StockException;
import com.jfsfeb.stockmanagementsystemwithjdbc.utility.JdbcUtilityForStock;



public class UserDaoImpl implements UserDao  {
	JdbcUtilityForStock dataBaseConnector = new JdbcUtilityForStock();
	
	public boolean registerUser(UserBean user) {
		
		try (Connection connection = dataBaseConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(dataBaseConnector.getSQLQuery("registerInvestor"))){
				
				preparedStatement.setInt(1, user.getId());
				preparedStatement.setString(2, user.getName());
				preparedStatement.setLong(3, user.getPhoneNumber());
				preparedStatement.setString(4, user.getMailId());
				preparedStatement.setString(5, user.getPassword());
				preparedStatement.setString(6, user.getRole());

				int p=preparedStatement.executeUpdate();
				System.out.println(p);
				return true;
			} catch (Exception e) {
				throw new StockException("Investor already registred ,please try to login");
			}
		}


	public UserBean loginUser(String email, String password){
		UserBean admin = new UserBean();

		try (Connection connection = dataBaseConnector.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(dataBaseConnector.getSQLQuery("investorLogin"))){
			
		        preparedStatement.setString(1, email);
				preparedStatement.setString(2, password);
				ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					UserBean bean = new UserBean();
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


	public boolean buyStock(UserBean user, StockBean stockBean) {
		BuyStockBean beans= new BuyStockBean();
		try (Connection connection = dataBaseConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(dataBaseConnector.getSQLQuery("buyStock1"));) {
			preparedStatement.setInt(1, stockBean.getId());
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				if(resultSet.getInt("id") == stockBean.getId())
				{
					try(Connection connection1 = dataBaseConnector.getConnection();
							PreparedStatement preparedStatement1 = connection1.prepareStatement(dataBaseConnector.getSQLQuery("buystock2"));) {

						preparedStatement1.setInt(1, user.getId());

						ResultSet resultSet1 = preparedStatement1.executeQuery();
						while (resultSet1.next()) {
						
							if(resultSet1.getInt("id") == user.getId()) {
								try (Connection connection2 = dataBaseConnector.getConnection();
										PreparedStatement preparedStatement3 = connection2.prepareStatement(dataBaseConnector.getSQLQuery("buystock3"))){
                                 
									if(stockBean.getNoOfProducts() <= resultSet.getInt("noOfProducts") 
                            		    && stockBean.getCost()<= resultSet.getInt("cost")) {
                                    	
									preparedStatement3.setInt(1, resultSet.getInt("id"));
									preparedStatement3.setString(2, resultSet.getString("companyName"));
									preparedStatement3.setInt(3, stockBean.getNoOfProducts());
									preparedStatement3.setInt(4,  stockBean.getCost());
									preparedStatement3.setString(5, resultSet.getString("typeOfStock"));
									preparedStatement3.setInt(6, resultSet1.getInt("id"));
									preparedStatement3.setString(7,resultSet1.getString("name"));

									int p=preparedStatement3.executeUpdate();
									System.out.println(p);
									
									
                                    if(p!=0) {
									      return true;
                                    }else {
                                    	return false;
                                    }
                              }else {
                            	  System.out.println("cost and no of products are overloaded for investment");
                              }
									
								} catch (SQLException e) {
									throw new StockException("stock already existed ,please add new stock");
								}

                            }else {
								System.err.println("investor id is not availble,please login first !!");
								return false;
							}

						}
					}
				}else {
					System.err.println("stock id is not available!!");
					return false;
				}
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
		
}
@Override
	public boolean changePassword(long mobile, String password) {
		try (Connection connection = dataBaseConnector.getConnection();
				 PreparedStatement preparedStatement = connection.prepareStatement(dataBaseConnector.getSQLQuery("changePassword2"));) {
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
	public boolean updateProfile(String mail, long phNum) {
		try (Connection connection = dataBaseConnector.getConnection();
				 PreparedStatement preparedStatement = connection.prepareStatement(dataBaseConnector.getSQLQuery("updateProfile"));) {

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
	public List<BuyStockBean> getAllBuyStockInfo() {
    	try (Connection connection = dataBaseConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(dataBaseConnector.getSQLQuery("getAllBuyStockInfo"));) {
			ResultSet resultSet = preparedStatement.executeQuery();
			List<BuyStockBean> beans = new ArrayList<BuyStockBean>();
			while (resultSet.next()) {
				BuyStockBean bean = new BuyStockBean();
				bean.setStockId(resultSet.getInt("stockId"));
				bean.setCompanyName(resultSet.getString("CompanyName"));
				bean.setNoOfProducts(resultSet.getInt("noOfProducts"));
				bean.setCost(resultSet.getInt("cost"));
				bean.setStockType(resultSet.getString("stockType"));
				bean.setUserId(resultSet.getInt("userId"));
				bean.setUserName(resultSet.getString("userName"));
				
				beans.add(bean);
			}
			return beans;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
