package com.jfsfeb.stockmanagementsystemwithjdbc.controllers;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.AdminBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.BuyStockBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.StockBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.UserBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.exceptions.StockException;
import com.jfsfeb.stockmanagementsystemwithjdbc.factory.StockFactory;
import com.jfsfeb.stockmanagementsystemwithjdbc.services.UserService;
import com.jfsfeb.stockmanagementsystemwithjdbc.services.UserServiceImpl;
import com.jfsfeb.stockmanagementsystemwithjdbc.validations.InputValidationsImpl;

import lombok.extern.log4j.Log4j;

@Log4j
@SuppressWarnings("unused")
public class InvestorController {
	public void investorController() {
		
		String regName = null; 
		long regMobile = 0;
		String regEmail = null;
		String regPassword = null;
		String regRole=null;

		int stockId = 0; 
		String stockCName = null;
		String stockType = null;
		int stcokNoOfProducts=0;
		int stockCost = 0;

		String loginMail=null;
		String loginPassword=null;

       log.info("<<<<>>>>--------welcome to investor blog-------<<<<>>>>");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		UserService service1 = StockFactory.getInstanceOfUserService();
		InputValidationsImpl validation = new InputValidationsImpl();

		try {
			do {
				try {
					log.info("<<<<<----------------------------------->>>>>");
					log.info("Press 1 to Investor Register");
					log.info("Press 2 to investor Login");
					log.info("press 3 to exit");
					log.info("<<<<<----------------------------------->>>>>>");
					int choice = scanner.nextInt();
					switch (choice) {

					case 1:
						Random random = new Random();
					    int id = random.nextInt(99);
						log.info(id <0?id *-1:id);
						log.info("Enter Name :");
						regName = scanner.next();
						log.info("Enter Mobile :");
						regMobile = scanner.nextLong();
						log.info("Enter Email :");
						regEmail = scanner.next();
						log.info("Enter Password :");
						regPassword = scanner.next();
						regRole="investor";
						UserBean bean1 = new UserBean();
						bean1.setId(id);
						bean1.setName(regName);
						bean1.setPhoneNumber(regMobile);
						bean1.setMailId(regEmail);
						bean1.setPassword(regPassword);
						bean1.setRole(regRole);				

						boolean check = service1.registerUser(bean1);
						if(check) {
							log.info("Registered");
						} else {
							log.info("Email already exist");
						}


						break;

					case 2 :
						log.info("enter login mail");
						regEmail=scanner.next();
						log.info("Enter  password");
						regPassword = scanner.next();
                        try {
							@SuppressWarnings("unused")
							UserBean login = service1.loginUser(regEmail, regPassword);
							String s= new String(login.getName());
                            if(login!=null) {
							log.info("Logged in Successfully");
							log.info("");
							log.info("<<<<------WELCOME "+s.toUpperCase()+"------>>>>");
							log.info("");
							do {
								try {
									log.info("<<-------------------------------------------->>");
									log.info("press 1 : forgot password");
									log.info("press 2 : to update mail");
									log.info("Press 3 : to Search the stock by company name");
									log.info("Press 4 : to Search the stock by Type of stock");
									log.info("Press 5 : to Get the stock Information");
									log.info("Press 6 : to buy stock ");
									log.info("press 7 : to see all buy stock information");
									log.info("Press 8 : to signout");
									log.info("<<-------------------------------------------->>");

									int choice2 = scanner.nextInt();
									switch (choice2) {

									case 1 :
										log.info("Enter Mobile :");
										regMobile  = scanner.nextLong();
										log.info("Enter new Password :");
										regPassword = scanner.next();

										if (regMobile == 0) {
											log.info("Enter the Valid phnumber");
										} else {

											Random random1 = new Random();
											int otp = random1.nextInt(9999);
											log.info(otp<0?otp*-1:otp);
											log.info("please ,enter otp ");
											int typeOtp = scanner.nextInt();
											if(otp==typeOtp) {

												UserBean bean6 = new UserBean();
												bean6.setPhoneNumber(regMobile);
												bean6.setPassword(regPassword);
												boolean update = service1.changePassword(regMobile,regPassword);
												if (update) {
													log.info("password updated succesfully");
												} else {
													log.error("passwrod is not updated");
												}
											}else {
												log.error("otp mismatched");
											}
										}
										break;

									case 2 :
										log.info("Enter mobile number :");
										regMobile = scanner.nextLong();

										log.info("Enter new Email :");
										regEmail = scanner.next();

										if (regMobile== 0) {
											log.info("Enter the Valid phnumber");
										} else {
											UserBean bean6 = new UserBean();
											bean6.setPhoneNumber(regMobile);
											bean6.setMailId(regEmail);
											boolean update = service1.updateProfile(regEmail, regMobile);
											if (update) {
												log.info("profile updated succesfully");
											} else {
												log.error("profile is not updated");
											}
										}
										break;

									case 3 :
										log.info("enter company name");
										String cName = scanner.next();

										List<StockBean> company = service1.searchProductByName(cName);
										if(company!=null) {
											log.info(String.format("%-5s %-20s %-20s %-20s %s", "Id",
													"company Name", "cost", "no of products", "type of product"));
											for (StockBean stockBean : company) {

												if (stockBean != null) {
													log.info(String.format("%-5s %-20s %-20s %-20s %s", stockBean.getId(),
															stockBean.getCompanyName(), stockBean.getCost(), stockBean.getNoOfProducts(), stockBean.getTypeOfStock()));
												} 
											}
										}else {
											log.error("No stocks are available with this company name!!");
										}
										break;

									case 4:
									
										log.info("Search the product by type :");
										String type = scanner.next();

										List<StockBean> beanType = service1.searchProductByType(type);
										if(beanType!=null) {

											log.info(String.format("%-5s %-20s %-20s %-20s %s", "Id",
													"cName", "no of products", "Cost", "type of stock"));

											for (StockBean bean : beanType) {	
												if (bean != null) {
													log.info(String.format("%-5s %-20s %-20s %-20s %s", bean.getId(),
															bean.getCompanyName(), bean.getNoOfProducts(), bean.getCost(), bean.getTypeOfStock()));
												}
											}
										}else {
											log.error("No stocks are available with this type!!");
										}
										break;


									case 5:
										List<StockBean> info = service1.getAllStcokInfo();
										log.info(String.format("%-5s %-20s %-20s %-20s %s",  "Id",
												"cName", "no of products", "Cost", "type of stock"));
										
										for (StockBean bean : info) {

											if (bean != null) {
												log.info(String.format("%-5s %-20s %-20s %-20s %s",bean.getId(),
														bean.getCompanyName(), bean.getNoOfProducts(), bean.getCost(), bean.getTypeOfStock()));
											} else {
												log.info("product information  is not present");
											}
										}
										
										break;
									case 6:
										log.info("Enter stock id");
										int bId = scanner.nextInt();
										StockBean stockBean = new  StockBean();
										stockBean.setId(bId);

										log.info("Enter user id");
										int userId = scanner.nextInt();
										UserBean userBean = new UserBean();
										userBean.setId(userId);
										
										log.info("Enter no of products to buy");
										int quantity=scanner.nextInt();
										stockBean.setNoOfProducts(quantity);
										
										log.info("Enter the amount of investment");
										int investment= scanner.nextInt();
										stockBean.setCost(investment);
										
                                        BuyStockBean bean= new BuyStockBean();
										try {
										boolean request = service1.buyStock(userBean, stockBean);
										if(request!=false) {
											       log.info("product added succefully to the cart");
											}else {
												log.error("prodcut not available");
											}
										} catch (StockException e) {

											log.info("product is not available with this id !!");
										}
										break;


								    case 7:
								    	List<BuyStockBean> stockBean1 = service1.getAllBuyStockInfo();
								    	log.info(String.format("%-5s %-20s %-20s %-20s %-20s %-20s %s",  "Id",
												"companyName", "no of products", "Cost", "type of stock","user-id","user-name"));
									
										for (BuyStockBean bean11 : stockBean1) {

											if (bean11 != null) {
												
												log.info(String.format("%-5s %-20s %-20s %-20s %-20s %-20s %s",bean11.getStockId(),
														bean11.getCompanyName(), bean11.getNoOfProducts(), bean11.getCost(),
														bean11.getStockType(),bean11.getUserId(),bean11.getUserName()));
											} else {
												log.info("product information  is not present");
											}
										}
							
						                  break;
									
									case 8:
										MainController.mainController();
										break;

									default:
										log.error("please enter numeric positive choice between 1-8");
										break;
									}


								}catch (Exception e) {
						             log.error(e.getMessage());
									 investorController();
									 
								}
							}while(true);
                            }else {
                            	log.info("invalid credentilas...please try again!!");
                            }
						}catch (Exception e) {
							log.error(e.getMessage());
						}
						break;

					case 3 :
						 MainController.mainController();

					default : 
						log.error("plsease enter positive choice betweem 1-3");

					}
				}catch (InputMismatchException e) {
					log.error(" please,enter valid numeric choice between 1-3");
					investorController();
				}catch (StockException e) {
					log.error(e.getMessage());
					investorController();
				}

            }while(true);
		}catch (StockException e) {
			log.error(e.getMessage());
		}


	}

}
