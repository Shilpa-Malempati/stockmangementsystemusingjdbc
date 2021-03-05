package com.jfsfeb.stockmanagementsystemwithjdbc.validations;

public interface InputValidation {
	public boolean validatedId(int id);
	public boolean validatedName(String name);
	public boolean validatedMobile(long mobile);
	public boolean validatedEmail(String email);
	public boolean validatedPassword(String password);
}
