package com.example.demo.Repository;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.Model.TransactionBean;

@Repository
public interface TransactionRepo  extends CrudRepository<TransactionBean,Long>{
	List<TransactionBean> findAll();
}