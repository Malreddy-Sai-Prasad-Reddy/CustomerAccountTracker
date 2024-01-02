package com.example.demo.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Model.AccountBean;
import com.example.demo.Service.AccountService;

@RestController
public class AccountController {
	
	@Autowired
	AccountService service;
	
	@GetMapping("/")
	public String main() {
		return "<center><h1>Hello, welcome to mahindraa bank</h1></center>";
	}
	
	@GetMapping("/getAllAccounts")
	public List<AccountBean> getAllAccount(){
		return service.getAllAccount();
	}
	
	@GetMapping("/getAccountById/{id}")
	public AccountBean getAccount(@PathVariable long id) {
		return service.getAccount(id);
	}
	
	@GetMapping("/getByAccountNumber/{accountNumber}")
	public AccountBean getByAccountNumber(@PathVariable Long accountNumber) {
		return service.getByAccountNumber(accountNumber);
	}
	
	
	
	
	
	//EXTRA
	@PostMapping("/addAccount")
	public void addAccount(@RequestBody AccountBean account) {
		
		 service.save(account);
	}
	//EXTRA
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable long id) {
		return service.delete(id);
	}
	//EXTRA
	@GetMapping("/update")
	public void update(@RequestBody AccountBean account) {
		service.save(account);
		
	}
//
}
