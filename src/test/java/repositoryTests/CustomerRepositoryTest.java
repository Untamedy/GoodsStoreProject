/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositoryTests;

import com.store.goodsstore.GoodsstoreApplication;
import com.store.goodsstore.entities.Customer;
import com.store.goodsstore.entities.Organization;
import com.store.goodsstore.entities.Role;
import com.store.goodsstore.repository.CustomerRepository;
import com.store.goodsstore.repository.OrganizationRepository;
import com.store.goodsstore.repository.RolesRepository;
import java.util.HashSet;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Lenovo
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = GoodsstoreApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepositoryTest {
    
    @Autowired
    private static OrganizationRepository orgRepository;
    
    @Autowired
    private CustomerRepository repository;
    
    private static Organization organization;
    
   @BeforeAll
    public static  void createEntity(@Autowired OrganizationRepository orgrepository,@Autowired RolesRepository rolesRepository) {  
        organization = new Organization();
        organization.setCode("2222");
        organization.setEmail("test2Org@mail.com");
        organization.setName("org2");
        orgrepository.save(organization);
       
    }
    
    
    
    @Test   
    public void save(){
        Customer customer = new Customer();
        customer.setName("cust");
        customer.setOrg(organization);
        customer.setPhoneNum("12345");
        assertThat(null!=repository.save(customer));
    }
            
    
    
}
