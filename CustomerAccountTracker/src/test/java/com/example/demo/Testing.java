package com.example.demo;
 
import static org.assertj.core.api.Assertions.assertThat;
 
import java.util.List;
 
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo.Model.AccountBean;
import com.example.demo.Model.CustomerBean;
import com.example.demo.Repository.AccountRepo;
import com.example.demo.Repository.CustomerRepo;
 

 
@SpringBootTest
public class Testing {
    @Autowired
    private CustomerRepo repo;
    @Autowired
    private AccountRepo aRepo;

    @Test
    public void saveCustomerTest() {
        CustomerBean customerBean = new CustomerBean();
        AccountBean accountBean = new AccountBean();
        customerBean.setAccountType("current");
        customerBean.setEmail("sai@gmail.com");
        customerBean.setGender("Male");
        customerBean.setName("sai");
        customerBean.setContact(95535122429l);
        customerBean.setAdharNo(475493340067l);
        accountBean.setAccountNumber(customerBean.getAccountNumber());
        accountBean.setAccountType(customerBean.getAccountType());
        accountBean.setName(customerBean.getName());
        repo.save(customerBean);
        aRepo.save(accountBean);
        assertThat(customerBean.getUserId()).isGreaterThan(0);
    }

    @Test
    public void getListOfCustomer() {
        List<CustomerBean> customerBean = (List<CustomerBean>) repo.findAll();
        assertThat(customerBean.size()).isGreaterThan(0);
    }
}

