/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceTests;

import com.store.goodsstore.GoodsstoreApplication;
import com.store.goodsstore.dto.UserDto;
import com.store.goodsstore.entities.Users;
import com.store.goodsstore.repository.UserRepository;
import com.store.goodsstore.services.UserService;
import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Lenovo
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = GoodsstoreApplication.class)
public class UserServiceTest {
    
    
    @Autowired 
   UserService service;
   
  
    @MockBean
    private UserRepository repository;
    
    @Before
    public void setup(){
        Users newUser = new Users();
        newUser.setEmail("example@mail.com");
        newUser.setName("User");
        
        Mockito.when(repository.findByEmail(newUser.getName())).thenReturn(newUser);
        
    }
    
    @Test
    public void getUserByEmail(){
        String name = "User";
        UserDto user = service.getUsersByEmail(name);
       assertThat(user.getUsername()).isEqualTo(name);    
    }
    
    
    
    
    
}
