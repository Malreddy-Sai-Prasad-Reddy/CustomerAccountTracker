package com.example.demo.Model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
@Component
public class AccountBean {	
	@Id
	private long userId;
	private long accountNumber;
	private String accountType;
	private String name;
	private long balanceAmount;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getBalanceAmount() {
		return balanceAmount;
	}
	public void setBalanceAmount(long balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	@Override
	public String toString() {
		return "AccountBean [userId=" + userId + ", accountNumber=" + accountNumber + ", accountType=" + accountType
				+ ", name=" + name + ", balanceAmount=" + balanceAmount + "]";
	}
	

}
