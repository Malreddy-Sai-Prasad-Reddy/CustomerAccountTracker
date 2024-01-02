package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.CustomerBean;
import com.example.demo.Repository.CustomerRepo;


@Service
public class CustomerService {
	
	
	@Autowired
	CustomerRepo repo;
	
	//Customer Service for CRUD Operations
	
	//Inserting Data. //Updating Data.
	public String save(CustomerBean customer) {
		repo.save(customer);
		return "<center><h1>Account created successfully</center><h1>";
	}
	
	
	
	//Fetching All Data.
	public List<CustomerBean> getAllCustomer() {
		
		return repo.findAll();
	}
	
	
	//Fetching Data by Id.
	public CustomerBean getCustomer(long id) {
		Optional<CustomerBean> optional=repo.findById(id);
		CustomerBean customer=null;
		
		if(optional.isPresent()) {
			customer=optional.get();
		}
		return customer;
	}
	
	
	//Deleting the Data.
	public String delete(long id) {
		
		repo.deleteById(id);
		return "<center><h1>Deleted successfully</center></h1>";
	}

}
