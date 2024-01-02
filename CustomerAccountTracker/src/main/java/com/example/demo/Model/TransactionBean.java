package com.example.demo.Model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Component
public class TransactionBean {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long sNo;
	@Column(name="userId_SENDER")
	private long userId;
	@Column(name="beneficiaryName_RECEIVER")
	private String beneficiaryName;
	@Column(name="beneficiaryAccountNo_RECEIVER")
	private long beneficiaryAccountNo;
	@Column(name="amount_toSEND")
	private long amount;
	@Column(name="balanceAmount_SENDER")
	private long balanceAmount;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getBeneficiaryName() {
		return beneficiaryName;
	}
	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}
	public long getBeneficiaryAccountNo() {
		return beneficiaryAccountNo;
	}
	public void setBeneficiaryAccountNo(long beneficiaryAccountNo) {
		this.beneficiaryAccountNo = beneficiaryAccountNo;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public long getBalanceAmount() {
		return balanceAmount;
	}
	public void setBalanceAmount(long balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
}
