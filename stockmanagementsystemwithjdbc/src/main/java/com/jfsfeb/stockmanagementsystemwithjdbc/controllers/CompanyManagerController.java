package com.jfsfeb.stockmanagementsystemwithjdbc.controllers;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import com.jfsfeb.stockmanagementsystemwithjdbc.dao.AdminDao;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.AdminBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.CompanyManagerBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.StockBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.UserBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.exceptions.StockException;
import com.jfsfeb.stockmanagementsystemwithjdbc.factory.StockFactory;
import com.jfsfeb.stockmanagementsystemwithjdbc.services.CompanyManagerService;
import com.jfsfeb.stockmanagementsystemwithjdbc.services.CompanyManagerServiceImpl;
import com.jfsfeb.stockmanagementsystemwithjdbc.validations.InputValidationsImpl;

import lombok.extern.log4j.Log4j;
@Log4j
@SuppressWarnings("unused")
public class CompanyManagerController {
   public void companyManagerController() {
		
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
      
		int regId = 0; 
		String regName = null; 
		long regMobile = 0;
		String regEmail = null;
		String regPassword = null;

        int stockId = 0; 
		String stockCName = null;
		String stockType = null;
		int stcokNoOfProducts=0;
		int stockCost = 0;

		String loginMail=null;
		String loginPassword=null;

        CompanyManagerService service2 = StockFactory.getInstanceOfCompanyMangerService();
        log.info("<<<<<<>>>>>----------welcome to company manger blog,plsease login---------<<<<<>>>>>");
		log.info("Enter Email :");
		loginMail = scanner.next();
		log.info("Enter Password :");
		loginPassword = scanner.next();
		log.info("");
		
		try {
			CompanyManagerBean login = service2.managerLogin(loginMail,loginPassword);
			if(login!=null) {
			log.info("You have logged in successfully");
			log.info("");
			String s= new String(login.getName());
			log.info("<<<<>>>>------------WELCOME "+s.toUpperCase()+"---------<<<<>>>>");
			log.info("");
			log.info("Now you can perform the following operations:-");
			log.info("");
			do{
				try {
					log.info("<<<<<--------------------------------->>>>>");
					log.info("Press 1  : to change password");
					log.info("press 2  : to add stock");
					log.info("press 3  : to update the type of stock");
					log.info("press 4  : to update the cost of stock");
					log.info("press 5  : to update the no of stock ");
					log.info("press 6  : to remove stock");
					log.info("press 7  : to search stock by stock type");
					log.info("press 8  : to search stock by company name");
					log.info("press 9  : to read the all stock");
					log.info("press 10 : to logout from this current blog");
					log.info("<<<<<------------------------------------>>>>");

					int choice = scanner.nextInt();
					switch(choice) {

					case 1 :
						log.info("Enter Mobile :");
						regMobile  = scanner.nextLong();
						log.info("Enter new Password :");
						regPassword = scanner.next();

						if (regMobile == 0) {
							log.info("Enter the Valid phnumber");
						} else {

							Random random = new Random();
							int otp = random.nextInt(9999);
							log.info(otp<0?otp*-1:otp);
							log.info("please ,enter otp ");
							int typeOtp = scanner.nextInt();
							if(otp==typeOtp) {

								CompanyManagerBean bean6 = new CompanyManagerBean();
								bean6.setPhoneNumber(regMobile);
								bean6.setPassword(regPassword);
								boolean update = service2.changePassword(regMobile,regPassword);
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
						try {
							
						log.info("Enter stock-ID :");
						stockId = scanner.nextInt();
						log.info("Enter company Name :");
						stockCName = scanner.next();
						log.info("Enter stock name :");
						stockType = scanner.next();
                        log.info("enter cost of product");
						stockCost= scanner.nextInt();
                        log.info("enter no of products");
						stcokNoOfProducts = scanner.nextInt();
						
						StockBean bean1 = new StockBean();
						bean1.setId(stockId);	
						bean1.setCompanyName(stockCName);
						bean1.setCost(stockCost);
						bean1.setTypeOfStock(stockType);
						bean1.setNoOfProducts(stcokNoOfProducts);
						boolean check2 = service2.addStock(bean1);
						if(check2) {
							log.info("stock Added succefully");
							log.info(String.format("%-5s %-20s %-20s %-20s %s",bean1.getId(),bean1.getCompanyName(),bean1.getCost(),
									bean1.getTypeOfStock(),bean1.getNoOfProducts()));
						} else {
							log.info("stock already exist");
						}
						}catch (InputMismatchException e) {
							log.error("please enter stock name with alphabets and id with didgits");
							scanner.next();
						}

						break;

					case 3 :
						log.info("enter  Stock_Id");
						int sId=scanner.nextInt();
						log.info("Enter the new stock_type :");
						String sType = scanner.next();
                        if (sId == 0) {
							log.info("Enter the Valid stock_id");
						} else {
							StockBean bean6 = new StockBean();
							bean6.setId(sId);
							bean6.setTypeOfStock(sType);
							boolean update = service2.updateStockTypeById(sId, sType);
							if (update) {
								log.info("stock updated succesfully");
							} else {
								log.error("stock-id is not available");
							}
						}

						break;
					case 4 :
						log.info("enter  Stock_Id");
						int sid=scanner.nextInt();
						log.info("Enter the new stock_Cost :");
						int sCost = scanner.nextInt();
                        if (sid==0) {
							log.info("Enter the Valid stock_Name");
						} else {
							StockBean bean6 = new StockBean();
							bean6.setCost(sCost);
							bean6.setId(sid);
							boolean update = service2.updateStockCostById(sid, sCost);
							if (update) {
								log.info("stock updated succesfully");
							} else {
								log.error("stock is not available");
							}
						}
                          break;

					case 5 :
						log.info("enter Stock_Id");
						int id=scanner.nextInt();
						log.info("Enter the  no of stock:");
						int count = scanner.nextInt();


						if (id==0) {
							log.info("Enter the Valid stock_type");
						} else {
							StockBean bean6 = new StockBean();
							bean6.setId(id);
							bean6.setNoOfProducts(count);
							boolean update = service2.updateStockCountById(id, count);
							if (update) {
								log.info("stock updated succesfully");
							} else {
								log.error("stock is not available");
							}
						}

						break;

					case 6 :
						log.info("Enter the stock_Id to delete :");
						int stockId1 = scanner.nextInt();
						if (stockId1 == 0) {
							log.info("Enter the Valid stock_Id");
						} else {
							StockBean bean6 = new StockBean();
							bean6.setId(stockId1);
							boolean remove = service2.removeStock(stockId1);
							if (remove) {
								log.error("The stock is removed succefully");
							} else {
								log.error("The stock is not availble with this id !!");
							}
						}
						break;

					case 7 :

						log.info("  enter the stock type :");
						String stockType1 = scanner.next();
						StockBean bean3 = new StockBean();
						List<StockBean> stockType2 = service2.searchProductByType(stockType1);
						
						if(stockType2!=null) {
							log.info(String.format("%-5s %-20s %-20s %-20s %s", "stock-Id",
									"company", "cost", "no of products","type of stock"));
							for (StockBean stockBean : stockType2) {	
								if (stockBean != null) {
									log.info(String.format("%-5s %-20s %-20s %-20s %s",  stockBean.getId(),
											stockBean.getCompanyName(), stockBean.getCost(), stockBean.getNoOfProducts(), stockBean.getTypeOfStock()));
								} 
							}
						}else {
							log.error("No stocks are available with this type.");
						}

						break;


					case 8 :

						log.info("enter the company Name:");
						String comName = scanner.next();

						List<StockBean> company = service2.searchProductByName(comName);
						if(company!=null) {
							log.info(String.format("%-5s %-20s %-20s %-20s %s", "stock-Id",
									"company", "cost", "no of products","type of stock"));
							for (StockBean stockBean : company) {

								if (stockBean != null) {

									log.info(String.format("%-5s %-20s %-20s %-20s %s", stockBean.getId(),
											stockBean.getCompanyName(), stockBean.getCost(), stockBean.getNoOfProducts(), stockBean.getTypeOfStock()));

								} 
							}
						}else {
							log.error("No stocks are available with this companyname");
						}

						break;

					case 9 :
						List<StockBean> info = service2.getAllStcokInfo();
						log.info(String.format("%-5s %-20s %-20s %-20s %s", "stock-Id",
								"company", "cost", "no of products","type of stock"));
						for (StockBean stockBean : info) {

							if (stockBean != null) {
								log.info(String.format("%-5s %-20s %-20s %-20s %s", stockBean.getId(),
										stockBean.getCompanyName(), stockBean.getCost(), stockBean.getNoOfProducts(), stockBean.getTypeOfStock()));

							} else {
								log.info("stock info is not present");
							}
						}
						break;

                    case 10 :
						MainController.mainController();
						break;
					default:
						log.error("please enter valid positive choice between 1-10");

					}

				}catch(StockException e) {
					log.error(e.getMessage());
					companyManagerController();
				}catch(InputMismatchException e) {
				       log.error("enter valid numeric choice onlyy");
				       companyManagerController();
				}
			}while(true);
			}else {
				log.info("invalid credentials,try login again!!");
			}
		}catch(StockException e) {
			log.error(e.getMessage());
		}


	}

}
