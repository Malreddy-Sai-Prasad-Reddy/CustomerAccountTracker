package com.example.demo.Repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.Model.CustomerBean;

@Repository
public interface CustomerRepo  extends CrudRepository<CustomerBean,Long>{
	List<CustomerBean> findAll();
	Optional<CustomerBean> findByaccountNumber(long accountNumber);
    Optional<CustomerBean> deleteByAccountNumber(long accountNumber);
}