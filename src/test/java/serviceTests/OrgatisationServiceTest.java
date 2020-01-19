/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceTests;

import com.store.goodsstore.repository.OrganizationRepository;
import com.store.goodsstore.services.OrganizationService;
import com.store.goodsstore.services.StoreService;
import com.store.goodsstore.services.UserService;
import org.junit.runner.RunWith;
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
public class OrgatisationServiceTest {
    
    @Configuration
    static class TestConfig{
        
        @Bean
        private OrganizationService getService(){
            return new OrganizationService();
        }    
}
    
    @Autowired
    private OrganizationService service;
    
    @MockBean
    private UserService userService;

    @MockBean
    private StoreService storeService;

    @MockBean
    OrganizationRepository repository;
    
}
