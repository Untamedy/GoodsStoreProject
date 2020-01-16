/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceTests;

import com.store.goodsstore.entities.Users;
import com.store.goodsstore.repository.UserRepository;
import com.store.goodsstore.services.RolesService;
import com.store.goodsstore.services.UserService;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Lenovo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Configuration
    static class TestConfig {
        @MockBean
        private PasswordEncoder encoder;

        @MockBean
        private RolesService roleService;

        @Bean
        public UserService userService() {
            return new UserService();
        }

    }
    

    @Autowired
    private UserService service;

   
    @MockBean
    private UserRepository repository;

    

    @Test
    public void getUserByEmail() {
        String name = "User";
        
         Users newUser = new Users();
        newUser.setEmail("example@mail.com");
        newUser.setName(name); 
        
         Mockito.when(repository.findByName(newUser.getName())).thenReturn(newUser);
        
        Users user = service.getByName(name);
        assertThat(user.getName()).isEqualTo(name);
    }

}
