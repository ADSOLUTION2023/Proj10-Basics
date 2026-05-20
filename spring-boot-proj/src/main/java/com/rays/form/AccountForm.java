package com.rays.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.rays.common.BaseForm;

public class AccountForm extends BaseForm{

	@NotEmpty(message = "Name is required")
	private String name;
	@NotEmpty(message = "DOB Name is required")
	private Date dob;
	@NotEmpty(message = "Account No. Name is required")
	private String accountNo;
	@NotEmpty(message = "Balance is required")
	private double balance;
	@NotEmpty(message = "Account Type is required")
	private String type;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

}
