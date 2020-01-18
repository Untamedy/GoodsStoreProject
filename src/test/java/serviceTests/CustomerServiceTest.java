/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceTests;

import com.store.goodsstore.dto.CustomerDto;
import com.store.goodsstore.dto.OrderDto;
import com.store.goodsstore.entities.Customer;
import com.store.goodsstore.entities.Order;
import com.store.goodsstore.entities.Organization;
import com.store.goodsstore.repository.CustomerRepository;
import com.store.goodsstore.services.CustomerService;
import com.store.goodsstore.services.IncomDocService;
import com.store.goodsstore.services.OrderService;
import com.store.goodsstore.services.OrganizationService;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Ignore;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Lenovo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

    private static Customer customer;
    private static Organization org;
    private static CustomerDto dto;

    @Configuration
    static class TestConfig {

        @Bean
        public CustomerService customerService() {
            return new CustomerService();
        }
    }

    @Autowired
    private CustomerService service;
    @MockBean
    private CustomerRepository repository;
    @MockBean
    private OrganizationService organizationService;
    @MockBean
    private OrderService orderService;
    @MockBean
    private IncomDocService incomeService;

    @BeforeAll
    public static void init() {
        org = new Organization();
        org.setId(1);
        org.setCode("1111");
        org.setEmail("orh@mail.com");
        org.setName("org");

        customer = new Customer();
        customer.setId(1);
        customer.setName("Customer");
        customer.setOrg(org);
        customer.setPhoneNum("121212");

        dto = new CustomerDto();
        dto.setName(customer.getName());
        dto.setOrgCode(customer.getOrg().getCode());
        dto.setPhone(customer.getPhoneNum());

    }

    @Test
    public void saveCustomer() {
        Customer cust = new Customer();

        Mockito.when(repository.existsByPhoneNum(customer.getPhoneNum())).thenReturn(false);       
        Mockito.when(organizationService.getByCode(customer.getOrg().getCode())).thenReturn(org);       
        Mockito.when(repository.save(customer)).thenReturn(customer);

        Customer newCustomer = service.saveCustomer(dto);

        assertThat(newCustomer.getName().equals(dto.getName()));

    }

    @Test
    public void editCustomer() {
        dto.setName("NewName");

        Mockito.when(organizationService.getByCode(customer.getOrg().getCode())).thenReturn(customer.getOrg());
        Mockito.when(repository.save(customer)).thenReturn(customer);

        assertThat(service.saveCustomer(dto).getName().equals(customer.getName()));
    }

    @Test
    public void deleteCustomer() {
        Mockito.when(repository.findByPhoneNumAndOrgCode(customer.getPhoneNum(), customer.getOrg().getCode())).thenReturn(customer);
        Mockito.when(orderService.getByCustomer(customer.getPhoneNum(), customer.getOrg().getCode())).thenReturn(new ArrayList<>());
        Mockito.when(incomeService.getByCustomer(customer)).thenReturn(new ArrayList<>());

        boolean result = service.deleteCustomer(customer.getPhoneNum(), customer.getOrg().getCode());

        assertTrue(result);

    }

    @Test
    public void getCustomerByName() {
        String name = "Customer";

        Mockito.when(repository.findByName(name)).thenReturn(customer);

        assertThat(name.equals(customer.getName()));

    }

    @Test
    public void getCustomerByPhoneAndOrgCode() {
        String code = "1111";
        String phone = "121212";

        Mockito.when(repository.findByPhoneNumAndOrgCode(phone, code)).thenReturn(customer);

        assertThat(phone.equals(customer.getPhoneNum()));

    }

    @Test
    public void getByOrgCode() {
        String code = "1111";
        List<Customer> customrs = new ArrayList<>();
        customrs.add(customer);

        Mockito.when(organizationService.getByCode(code)).thenReturn(customer.getOrg());
        Mockito.when(repository.findByOrgId(org.getId())).thenReturn(customrs);

        assertThat(!service.getByOrgCode(code).contains(customer));
    }

}
