package com.rays.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "st_account")
public class AccountDTO extends BaseDTO {

	@Column(name = "NAME", length = 50)
	private String name;
	@Column(name = "DOB", length = 50)
	private Date dob;
	@Column(name = "ACCOUNT_NO", length = 50)
	private String accountNO;
	@Column(name = "BALANCE", length = 50)
	private double balance;
	@Column(name = "TYPE", length = 50)
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

	public String getAccountNO() {
		return accountNO;
	}

	public void setAccountNO(String accounrNO) {
		this.accountNO = accounrNO;
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
