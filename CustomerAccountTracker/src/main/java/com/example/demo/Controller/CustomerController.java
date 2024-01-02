package com.example.demo.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Model.AccountBean;
import com.example.demo.Model.CustomerBean;
import com.example.demo.Service.CustomerService;


@RestController
public class CustomerController {
	
	@Autowired
	CustomerService service;
	
	@Autowired
	AccountController accountController;
	
	@Autowired
	AccountBean accountBean;
	@Autowired
	AccountBean ab;
	@Autowired
	AccountBean accountBean2 ;
	
	@Autowired
	CustomerBean customerBeann;
	
	
	private static String errorDisplay;

@RequestMapping("/testingjsp")
public String testingjsp() {
	return "testingjsp.jsp";
}
	
	
	@PostMapping("/addCustomer")
	public String addCustomer(@RequestBody CustomerBean customer) {
		CustomerBean customerCheck = getCustomer(customer.getUserId());
		if(customerCheck!=null) {
			return "<center><h1>Already user exist.</h1></center>";
		}
		String status = scan(customer);
		if(status.equals("failed")) {
			return (errorDisplay+"</h1></center>");
		}
		@SuppressWarnings("removal")
		Long initialBalance = new Long(5000);
		accountBean.setUserId(customer.getUserId());
		accountBean.setAccountNumber(customer.getAccountNumber());
		accountBean.setAccountType(customer.getAccountType());
		accountBean.setName(customer.getName());
		accountBean.setBalanceAmount(initialBalance);	
		
		accountController.addAccount(accountBean);
		return service.save(customer);
	}
	
	
	@GetMapping("/getAllCustomerDetails")
	public List<CustomerBean> getAllCustomer(){
		return service.getAllCustomer();
	}
	
	
	@GetMapping("/getCustomerById/{id}")
	public CustomerBean getCustomer(@PathVariable long id) {
		customerBeann = service.getCustomer(id);
		
		return customerBeann;
	}
	
	@DeleteMapping("/deleteCustomer/{id}")
	public String deleteCustomer(@PathVariable long id) {
		try{
			
			ab = accountController.getAccount(id);
			long money = ab.getBalanceAmount();
			accountController.delete(id);
			return service.delete(id)+("<br><center><h2>Here take these amount held in your account previously<br>"+money+"INR"+" <br>Have a great day</h2></center>");
		}
		catch(Exception e) {
			return "<center><h1>No account found to delete.</h1.</center>";
		}
		
	}
	
	
	@PutMapping("/updateCustomerDetails")
	public String updateCustomer(@RequestBody CustomerBean customer) {
		try{
			AccountBean ab = new AccountBean();
			ab = accountController.getAccount(customer.getUserId());
			if(ab==null) {
				return "<center><h1>No user found with that userid</h1></center>";
			}
			String status = scan(customer);
			if(status.equals("failed")) {
				return (errorDisplay+"</h1></center>");
			}
			
			
			accountBean2.setUserId(customer.getUserId());
			accountBean2.setAccountNumber(customer.getAccountNumber());
			accountBean2.setAccountType(customer.getAccountType());
			accountBean2.setName(customer.getName());
			accountBean2.setBalanceAmount(ab.getBalanceAmount());
			
			if((ab.toString()).equals(accountBean2.toString())) {
				return "<center><h1>No Changes held to update</h1></center>";
			}
			accountController.update(accountBean2);
			
			service.save(customer);
			return "<center><h1>Updated successfully</h1></center>";
		}
		catch(Exception e) {
			return "<center><h1>Invalid Details Please try Again</h1></center>";
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	public String scan(CustomerBean custommer) {
		try {
			
				errorDisplay = "<center><h1>";
				if(custommer.getName().length()<1) {
					errorDisplay += "Invalid Name<br>";
				}
				if(true != (custommer.getGender().equalsIgnoreCase("male") || custommer.getGender().equalsIgnoreCase("female")|| custommer.getGender().equalsIgnoreCase("transgender"))) {
					errorDisplay += "Invalid Gender<br>";
				}
				
				if(true != (custommer.getEmail().contains("@")&(custommer.getEmail().contains(".")))) {
					errorDisplay += "Invalid email<br>";
				}
				
				if(true!= (custommer.getContact()>999999999 & custommer.getContact()<(long)10000000000l)) {
					errorDisplay += "Invalid Phone Number<br>";
				}
				
				if(true != (custommer.getAdharNo()>(long)99999999999l & custommer.getAdharNo()<(long)1000000000000l)) {
					errorDisplay += "Invalid aadharNumber<br>";
				}
				
				if(true != (custommer.getAccountType().equalsIgnoreCase("savings") | custommer.getAccountType().equalsIgnoreCase("current")| custommer.getAccountType().equalsIgnoreCase("joint")| custommer.getAccountType().equalsIgnoreCase("individual"))) {
					errorDisplay += "Invalid AccountType<br>";
				}
				
				if(true != (custommer.getAccountNumber() > 99999 & custommer.getAccountNumber() < 1000000l)) {
					errorDisplay += "Invalid Account number, must be 6 digits";
				}
				
				if(errorDisplay.length() == 12) {
					return "passed";
				}
				else {
					Exception e = new Exception();
					throw e;
				}
			}
			catch(Exception e) {
				return "failed";
			}
	}
			
}
