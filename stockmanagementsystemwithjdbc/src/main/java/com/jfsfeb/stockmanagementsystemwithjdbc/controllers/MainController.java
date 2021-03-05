package com.jfsfeb.stockmanagementsystemwithjdbc.controllers;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.jfsfeb.stockmanagementsystemwithjdbc.exceptions.StockException;
import com.jfsfeb.stockmanagementsystemwithjdbc.validations.InputValidationsImpl;

import lombok.extern.log4j.Log4j;

@Log4j
public class MainController {
	public static void mainController(){
	    @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		do {
			try {
				log.info("<<<<<>>>>>-----------WELCOME TO STOCK MANAGEMNET SYSTEM----------<<<<<>>>>>");
				log.info("Press 1 : Admin ");
				log.info("press 2 : companymanager ");
				log.info("Press 3 : Investor ");
				log.info("-----------------------------------");

				int i = scanner.nextInt();
				
				if(Pattern.matches("^[0-9]", String.valueOf(i)) ) {
					
					switch(i) {

					case 1:
						AdminController adminController= new AdminController();
						adminController.adminController();
						break;

					case 2:
						CompanyManagerController managerContoller= new CompanyManagerController();
						managerContoller.companyManagerController();
						break;

					case 3:
						InvestorController controller= new InvestorController();
						controller.investorController();
						break;

					default :
						log.error("enter choice between 1-3");
					}
				}else {
					log.error("please, enter positive numbers only...!!");
				}
			}	catch (InputMismatchException e) {
				   log.error ("please, enter Numeric choice only...!!");
							 mainController();
			}
			
        }while(true);
		
	}



}
