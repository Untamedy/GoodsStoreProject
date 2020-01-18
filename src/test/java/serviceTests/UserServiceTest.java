/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceTests;

import com.store.goodsstore.dto.RegistrationRequest;
import com.store.goodsstore.dto.UserDto;
import com.store.goodsstore.entities.Organization;
import com.store.goodsstore.entities.Users;
import com.store.goodsstore.repository.UserRepository;
import com.store.goodsstore.services.RolesService;
import com.store.goodsstore.services.UserService;
import java.util.HashSet;
import static org.assertj.core.api.Assertions.assertThat;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Lenovo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    private static Users newUser;
    private static  RegistrationRequest request;

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

    @BeforeAll
    public static void init() {
        newUser = new Users();
        newUser.setEmail("example@mail.com");
        newUser.setName("User");
        newUser.setOrg(new Organization());
        newUser.setPassword("123");
        newUser.setId(1);
        newUser.setRoles(new HashSet<>());
        
        request = new RegistrationRequest();         
        request.setOrganizationEmail("org@email.com");
        request.setOrganizationName("orgName");
        request.setStoreName("StoreName");
        request.setUserEmail("example@mail.com");
        request.setUserName("User");
        request.setUserPass("123");

    }

    @Test
    public void getUserByName() {
        String name = "User";
        Mockito.when(repository.findByName(name)).thenReturn(newUser);
        Users userByName = service.getByName(name);

        assertThat(userByName.getName()).isEqualTo(name);
    }

    @Test
    public void getUserByEmail() {
        String email = "example@mail.com";
        Mockito.when(repository.findByEmail(email)).thenReturn(newUser);
        UserDto userByEmail = service.getUsersByEmail(email);

        assertThat(userByEmail.getUserEmail().equals(email));
    }

    @Test
    public void saveUser() {
        Mockito.when(repository.save(newUser)).thenReturn(newUser);
        Users savedUser = service.saveUser(newUser);
        assertThat(savedUser.getName().equals(newUser.getName()));

    }
    
    @Test
    public void editUser(){       
        request.setUserName("NewName");  
        Mockito.when(repository.existsByEmail(request.getUserEmail())).thenReturn(true);
        Mockito.when(repository.findByEmail(request.getUserEmail())).thenReturn(newUser);
        Mockito.when(repository.save(newUser)).thenReturn(newUser);
         UserDto editUser = service.editUser(request);
        assertThat(editUser.getUsername().equals(request.getUserName()));
    }
    
    @Test
    public void changePassword(){
        String newPass = "1234";  
        Mockito.when(repository.findByEmail(newUser.getEmail())).thenReturn(newUser);
        Mockito.when(repository.save(newUser)).thenReturn(newUser);
        
        boolean editPass = service.changePassword(newPass, newUser.getEmail());
        assertTrue(editPass);        
    }
    
    @Test
    public void createUser(){
        Mockito.when(repository.findByEmail("user@mail.com")).thenReturn(null);
        Users createdUser = service.createUser(request);        
        assertThat(createdUser.getName().equals(request.getUserName()));
    }

}
