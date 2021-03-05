package com.jfsfeb.stockmanagementsystemwithjdbc.controllers;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.AdminBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.CompanyBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.CompanyManagerBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.StockBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.UserBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.exceptions.StockException;
import com.jfsfeb.stockmanagementsystemwithjdbc.factory.StockFactory;
import com.jfsfeb.stockmanagementsystemwithjdbc.services.AdminService;

import lombok.extern.log4j.Log4j;

@Log4j
@SuppressWarnings("unused")
public class AdminController {
	public void adminController() {
		int regId = 0; 
		String regName = null; 
		long regMobile = 0;
		String regEmail = null;
		String regPassword = null;
		String regRole= null;

		int stockId = 0; 
		String stockCName = null;
		String stockType = null;
		int stcokNoOfProducts=0;
		int stockCost = 0;

		String loginMail=null;
		String loginPassword=null;

		int companyId = 0;
		String companyName=null;

		@SuppressWarnings("resource")
		Scanner scanner= new Scanner(System.in);

		AdminService service = StockFactory.getInstanceOfAdminService();
		
		log.info("<<<<>>>>-------------welcome to admin blog,please login------------<<<<<>>>>");
		log.info("");
		log.info("Enter email for login :");
		loginMail = scanner.next();
		log.info("Enter Password :");
		loginPassword = scanner.next();
		log.info("");

		try {
			AdminBean login = service.adminLogin(loginMail,loginPassword);
			String s= new String(login.getName());
			if(login!=null) {
			log.info("You have logged in successfully");
			log.info("");
			log.info(" <<<<<>>>>>-------------WELCOME "+s.toUpperCase()+"------------<<<<>>>>>");
			log.info("");
			log.info("Now you can perform the following operations:-");
			do{
				try {
					log.info("<<<<<<----------------------------------->>>>>>");
					log.info("Press 1  : to add company manager");
					log.info("press 2  : to remove companymanger");
					log.info("press 3  : to update company manger");
					log.info("press 4  : to add company");
					log.info("press 5  : to update company");
					log.info("press 6  : to remove company");
					log.info("press 7  : to see all investors");
					log.info("press 8  : to see all stocks");
					log.info("press 9  : to see all company mangers");
					log.info("press 10 : to see all companies ");
					log.info("Press 11 : to signout from current page");
					log.info("<<<<<<---------------------------------->>>>>>");

					int choice = scanner.nextInt();
					switch(choice) {

					case 1 :
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
						regRole="companyManager";
						
					     CompanyManagerBean bean1 = new CompanyManagerBean();
					     
					   	bean1.setId(id );
						bean1.setName(regName);
						bean1.setPhoneNumber(regMobile);
						bean1.setMailId(regEmail);
						bean1.setPassword(regPassword);
						bean1.setRole(regRole);				

						boolean check = service.registerCompanyManager(bean1);
						if(check) {
							log.info("Registered");
						} else {
							log.info("Email already exist");
						}


						break;

					   case 2 :
                	   log.info("Enter the  manager_Id to remove :");
                	   regId = scanner.nextInt();
                	   if (regId  == 0) {
                		   log.info("Enter the Valid manager_Id :");
                	   } else {
                		   CompanyManagerBean managerBean = new CompanyManagerBean();
                		   managerBean.setId(regId );
                		   boolean remove = service.removeManager(regId );
                		   if (remove) {
                			   log.error("company manager removed succesfully");
                		   } else {
                			   log.error("company manager is not available");
                		   }
                	   }

                	   break;   

                  case 3 :
                	  log.info("Enter Mobile number :");
                	  regMobile = scanner.nextLong();
                	  log.info("Enter new Email :");
                	  regEmail = scanner.next();
                	  if (regMobile == 0) {
                		  log.info("Enter the Valid phnumber");
                	  } else {
                		  CompanyManagerBean managerBean = new CompanyManagerBean();
                		  managerBean.setPhoneNumber(regMobile);
                		  managerBean.setMailId(regEmail);
                		  boolean update = service.updateManager(regEmail, regMobile);
                		  if (update) {
                			  log.info("company manager updated succesfully");
                		  } else {
                			  log.error("company manager is not updated");
                		  }
                	  }

                         break;
                    case 4 :
                    	log.info("Enter company name :");
                    	companyName = scanner.next();
                    	log.info("Enter company id :");
                    	companyId = scanner.nextInt();
                    	CompanyBean bean = new CompanyBean();
                    	bean.setCompName(companyName);
                    	bean.setCompanyId(companyId);
                    	boolean check2 = service.addCompany(bean);
                    	if(check2) {
                    		log.info("company added succefully");
                    		log.info(String.format("%-5s",bean.getCompName()));
                    	} else {
                    		log.info("company  already exist");
                    	}
                         break;
                     case 5:
						log.info("enter  company_Id");
						int cId=scanner.nextInt();
						log.info("Enter the  new company_name :");
						String cName = scanner.next();
                        if (cId == 0) {
							log.info("Enter the Valid company_Id");
						} else {
							CompanyBean companyBean = new CompanyBean();
							companyBean.setCompanyId(cId);
							companyBean.setCompName(cName);
							boolean update = service.updateCompany(cName,cId);
							if (update) {
								log.info("company updated succesfully");
							} else {
								log.error("company is not availble to update");
							}
						}
                        break;
                     case 6 :
                    	 log.info("Enter company name :");
                    	 companyName = scanner.next();
                    	 if (companyName == null) {
                    		 log.info("Enter the Valid company_name");
                    	 } else {
                    		 CompanyBean companyBean = new CompanyBean();
                    		 companyBean.setCompName(companyName);
                    		 boolean remove = service.removeCompany(companyName);
                    		 if (remove) {
                    			 log.error("company removed succesfully");
                    		 } else {
                    			 log.error("company is not available");
                    		 }
                    	 }
                      break;
                    case 7 :
                    	List<UserBean> info1 = service.showUsers();
                    	log.info(String.format("%-5s %-20s %-20s %-20s %s", "Id",
                    			"Name","number", "mail", "password"));
                    	for (UserBean userBean : info1) {
                    		if (userBean != null) {
                    			log.info(String.format("%-5s %-20s %-20s %-20s %s", userBean.getId(),
                    					userBean.getName(),userBean.getPhoneNumber(), userBean.getMailId(),userBean.getPassword()));
                    		} else {
                    			log.info("no users are available");
                    		}
                    	}

                    	break;

					case 8 :
						List<StockBean> info = service.getAllStcokInfo();
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

					case 9  :
						List<CompanyManagerBean> info2 = service.getAllCompanyManagerInfo();
						log.info(String.format("%-5s %-20s %-20s %-20s %s", "Id",
								"Name","number", "mail", "password"));
						for (CompanyManagerBean managerBean : info2) {
							if (managerBean != null) {
								log.info(String.format("%-5s %-20s %-20s %-20s %s", managerBean.getId(),
										managerBean.getName(),managerBean.getPhoneNumber(), managerBean.getMailId(),managerBean.getPassword()));

							} else {
								log.info("no managers are available");
							}
						}

						break;
                    case 10 :		
                    	List<CompanyBean> info3 = service.getAllCompanies();
                    	log.info(String.format("%-5s %-20s", "company-Id","company_name"));
                    	for (CompanyBean companyBean : info3) {

                    		if (companyBean != null) {
                    			log.info(String.format("%-5s %-20s", companyBean.getCompanyId(),
                    					companyBean.getCompName()));

                    		} else {
                    			log.info("company info is not present");
                    		}
                    	}
                           break;
					case 11 :
						MainController.mainController();

						break;
					default : 
						log.error("plese enter valid choice between 1-11");
					}

				}catch(InputMismatchException e) {
					log.error("please enter numeric choice only!!");
					adminController();
				}catch (StockException e) {
					log.error(e.getMessage());
				}
			}while(true);		
		}else {
			log.error("invalid credentials,login first!!!");
		}
		}catch(StockException e ) {

			log.error(e.getMessage());
		}

	}

}
