package com.example.demo.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Model.AccountBean;
import com.example.demo.Repository.AccountRepo;


@Service
public class AccountService {
	@Autowired
	AccountRepo repo;

	//Fetching all Accounts.
	public List<AccountBean> getAllAccount() {
		return repo.findAll();
	}

	//Fetching Specified account with id.
	public AccountBean getAccount(long id) {
		Optional<AccountBean> optional=repo.findById(id);
		AccountBean account=null;
		if(optional.isPresent()) {
			account=optional.get();
		}
		return account;
	}
	
	//Fetching Specified account by Account Number.
	public AccountBean getByAccountNumber(Long accountNumber) {
		Optional<AccountBean> optional=repo.findByAccountNumber(accountNumber);
		AccountBean account = null;
		if(optional.isPresent()) {
			account = optional.get();
		}
		return account;
	}
	
	
	
	//Extras
	public void save(AccountBean account) {
		repo.save(account);
	}
	
	public String delete(long id) {
		repo.deleteById(id);
		return "<center><h1>Deleted successfully</h1></center>";
	}
}