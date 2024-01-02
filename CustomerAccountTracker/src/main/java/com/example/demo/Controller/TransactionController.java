package com.example.demo.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Model.AccountBean;
import com.example.demo.Model.TransactionBean;
import com.example.demo.Service.TransactionService;


@RestController
public class TransactionController {

	@Autowired
	TransactionService service;
	@Autowired
	AccountController accountController;
	@Autowired
	AccountBean ab;
	
	//Transaction Maker
	@PostMapping("/makeTransaction")
	public String makeTransaction(@RequestBody TransactionBean transactionBean) {
		return service.makeTransaction(transactionBean);
	}
	
	
	@PostMapping("/deposit")
	public String makeDeposit(@RequestBody TransactionBean transactionBean) {
		try{
			ab = accountController.getAccount(transactionBean.getUserId());
			if((ab==null )| (transactionBean.getBeneficiaryAccountNo()!=ab.getAccountNumber())) {
				Exception e = new Exception();
				throw  e;
			}
			long toDeposit = transactionBean.getAmount();
			ab.setBalanceAmount(ab.getBalanceAmount()+toDeposit);
			transactionBean.setBeneficiaryName(ab.getName());
			transactionBean.setBalanceAmount(ab.getBalanceAmount());
			service.save(transactionBean);
			return service.deposit(ab);
		}
		catch(Exception e) {
			return "<center><h1>Invalid details Please try again</h1></center>";
		}
	}
	
	@PostMapping("/withdraw")
	public String makeWithDrawal(@RequestBody TransactionBean transactionBean) {
		try{
			ab = accountController.getAccount(transactionBean.getUserId());
			if((ab==null )| (transactionBean.getBeneficiaryAccountNo()!=ab.getAccountNumber())) {
				Exception e = new Exception();
				throw  e;
			}
			if(ab.getBalanceAmount()<transactionBean.getAmount()) {
				return "<center><h1>Insufficient Balanace</h1><center>";
			}
			long toWithdraw = transactionBean.getAmount();
			ab.setBalanceAmount(ab.getBalanceAmount()-toWithdraw);
			transactionBean.setBeneficiaryName(ab.getName());
			transactionBean.setBalanceAmount(ab.getBalanceAmount());
			
			service.withdraw(ab);
			transactionBean.setAmount(-toWithdraw);
			service.save(transactionBean);
			
			return "<center><h1>Withdrawal successfull<br>Here take these "+toWithdraw+" INR<br>Have a Great Day.";
		}
		catch(Exception e) {
			return "<center><h1>Invalid details Please try again</h1></center>";
		}
	}
	
	
	
	
	
	
	
	
	//**********************************
	//Extras					//Extras
	
	@PostMapping("/addTransaction")
	public void addTransaction(@RequestBody TransactionBean transaction) {
		
		service.save(transaction);
	}
	
	@GetMapping("/getAllTransaction")
	public List<TransactionBean> getAllTransaction(){
		return service.getAllTransaction();
	}
	
	@GetMapping("/getTransactionById/{id}")
	public TransactionBean getTransaction(@PathVariable long id) {
		return service.getTransaction(id);
	}
	
	
	@DeleteMapping("/deleteTransaction/{id}")
	public String deleteTransaction(@PathVariable long id) {
		return service.delete(id);
	}
	
	@GetMapping("/updateTransaction")
	public String updateTransaction(@RequestBody TransactionBean transaction) {
		service.save(transaction);
		return "update successfully";
		
		
	}
	@Autowired
	TransactionService ts;
	
	@PutMapping("/transfer/{accountNumber}")
	public String AmountTransfer(@PathVariable("accountNumber") Long accountNumber,TransactionBean tb, AccountBean ab)
	{
		return ts.AmountTransfer(accountNumber,tb,ab);
	}

}
