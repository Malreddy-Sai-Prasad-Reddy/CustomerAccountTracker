package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Controller.AccountController;
import com.example.demo.Model.AccountBean;
import com.example.demo.Model.TransactionBean;
import com.example.demo.Repository.AccountRepo;
import com.example.demo.Repository.TransactionRepo;

@Service
public class TransactionService {
	
	
	@Autowired
	TransactionRepo repo;

	@Autowired
	AccountController accountController;
	
	@Autowired
	AccountBean sender;
	@Autowired
	AccountBean reciever;
	@Autowired
	TransactionBean transaction= null;
	
	//Project Requirement #Transaction Manager
	public String makeTransaction(TransactionBean transactionBean) {
		
		try {
		sender = accountController.getAccount(transactionBean.getUserId());
		reciever = accountController.getByAccountNumber(transactionBean.getBeneficiaryAccountNo());
		
		if(sender.getBalanceAmount()>= transactionBean.getAmount()) {
			reciever.setBalanceAmount(reciever.getBalanceAmount()+transactionBean.getAmount());
			sender.setBalanceAmount(sender.getBalanceAmount()-transactionBean.getAmount());
			
			accountController.update(reciever);
			accountController.update(sender);
			
			transactionBean.setBeneficiaryName(reciever.getName());
			transactionBean.setBalanceAmount(sender.getBalanceAmount());
			
			save(transactionBean);
			return "<center><h1>Successfully Transferred</h1></center>";
			
		}
		}
		catch(Exception e) {
			return "<center><h1>Insufficient balance or invalid Details</h1><center>";
		}
		return "<center><h1>Insufficient balance or invalid Details</h1><center>";
	}
	
	//Deposit
	public String  deposit(AccountBean accountBean) {
		accountController.update(accountBean);
		return "<center><h1>Deposit Successfull</h1></center>";
	}
	
	//Withdrawal
	public void withdraw(AccountBean accountBean) {
		accountController.update(accountBean);
	}
	
	
	
	
	
	
	//Extras
	public void save(TransactionBean transaction) {
		// TODO Auto-generated method stub
		repo.save(transaction);
		
	}

	public List<TransactionBean> getAllTransaction() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	public TransactionBean getTransaction(long id) {
		// TODO Auto-generated method stub
		
		Optional<TransactionBean> optional=repo.findById(id);
		if(optional.isPresent()) {
			transaction=optional.get();
		}
		return transaction;
	}

	public String delete(long id) {
		// TODO Auto-generated method stub
		
		repo.deleteById(id);
		return "deleted successfully";
	}
	
	
	
	
	
	
	
	
	//Extra practice
	@Autowired
	TransactionRepo td;
	
	@Autowired
	AccountRepo ad;
	
	public String AmountTransfer(long accountNumber,TransactionBean tb,AccountBean ab)
	{
		Optional<AccountBean> transaction=ad.findByAccountNumber(accountNumber);
		String result="";
		if(transaction.isPresent())
		{
			tb.setAmount(tb.getAmount());
			tb.setBeneficiaryAccountNo(tb.getBeneficiaryAccountNo());
			tb.setBeneficiaryName(tb.getBeneficiaryName());
			AccountBean acc=transaction.get();
			if(acc.getBalanceAmount()>tb.getAmount())
			{
				tb.setBalanceAmount(acc.getBalanceAmount()-tb.getAmount());
				td.save(tb);
				acc.setBalanceAmount(acc.getBalanceAmount()-tb.getAmount());
				ad.save(acc);
				result = result + "Transaction Successfull";
			}
			else
			{
				result = result + "Max amount available for transaction is "+acc.getBalanceAmount();
			}
		return result;
		}
		else
		{
			return "Account Number Not Found";
		}
	}
}
