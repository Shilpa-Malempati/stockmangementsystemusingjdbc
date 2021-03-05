package com.jfsfeb.stockmanagementsystemwithjdbc.validations;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

import com.jfsfeb.stockmanagementsystemwithjdbc.exceptions.StockException;

public class InputValidationsImpl implements InputValidation{
	public boolean validatedId(int id){
		String idRegEx = "[0-9]{1}[0-9]{1}" ;
		boolean result = false;
		if (Pattern.matches(idRegEx, String.valueOf(id))) {
			result = true;
		} else {
			throw new StockException("Id should contains exactly 2 digits");
		}
		return result;
	}
	public boolean validatedName(String name){
		String nameRegEx = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$" ;
		boolean result = false;
		Pattern pattern = Pattern.compile(nameRegEx);
		Matcher matcher = pattern.matcher(name);
		if (matcher.matches()) {
			result = true;
		} else {
			throw new StockException("Name should  contains only Alphabates");
		}
		return result;
	}
	public boolean validatedMobile(long mobile){
		String mobileRegEx = "(0/91)?[6-9][0-9]{9}" ;
		boolean result = false;
		if (Pattern.matches(mobileRegEx, String.valueOf(mobile))) {
			result = true;
		} else {
			throw new StockException("Mobile Number  will start with  6 or 9 and It should contains 10 numbers");
		}
		return result;
	}
	public boolean validatedEmail(String email){
		String emailRegEx = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";
		boolean result = false;
		Pattern pattern = Pattern.compile(emailRegEx);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			result = true;
		}
		else {
			throw new StockException("Email should contain sequence of characters followed by @  and .com	");
		
		}
		return result;
	}
	public boolean validatedPassword(String password){
		String passwordRegEx = "^(?=[^\\d_].*?\\d)\\w(\\w|[!@#$%]){7,20}" ;
		boolean result = false;
		if (Pattern.matches(passwordRegEx, String.valueOf(password))) { 
			result = true;
		} else {
			throw new StockException("Password should contain atleast 5 characters ,one uppercase,one lowercase,one symbol"); 
		}
		return result;
	}
	public boolean validatedLoginEmail(String email){
		String emailLoginEx = "^\\w+@[a-zA-Z_]+?\\\\.[a-zA-Z]{2,3}$";
		boolean result = false;
		Pattern pattern = Pattern.compile(emailLoginEx);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			result = true;
		} else {
			throw new StockException("Email should contain sequence of characters followed by @  and .com");
		}
		return result;
	}
	public boolean validatedLoginPassword(String password){
		String passwordLoginEx = "^(?=[^\\d_].*?\\d)\\w(\\w|[!@#$%]){7,20}" ;
		boolean result = false;
		if (Pattern.matches(passwordLoginEx, String.valueOf(password))) { 
			result = true;
		} else {
			throw new StockException("Password should contain atleast 5 characters ,one uppercase,one lowercase,one symbol and number"); 
		}
		return result;
	}

}
