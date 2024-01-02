package com.example.demo.Repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.Model.AccountBean;

@Repository
public interface AccountRepo extends CrudRepository<AccountBean,Long> {
	List<AccountBean> findAll();
	Optional<AccountBean> findByAccountNumber(long accountNumber);
}