package com.store.goodsstore.services;

import com.store.goodsstore.dto.RegistrationRequest;
import com.store.goodsstore.dto.UserDto;
import com.store.goodsstore.entities.Role;
import com.store.goodsstore.entities.Users;
import com.store.goodsstore.exceptions.RegistrationException;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.store.goodsstore.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author YBolshakova
 */


@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository repositary;
    @Autowired
    RolesService rolesServise;
    @Autowired
    PasswordEncoder encoder;
    
    public Users saveUser(Users user){
     return repositary.save(user);
        
    }

    public UserDto getUsersByEmail(String email) {
        UserDto userDto = null;
        Users user = repositary.findByEmail(email);
        if (null != user) {
            logger.debug("User by email "+ email +" foundet");
            return userDto = createUserDto(user);
        }        
        return userDto;
    }

    public UserDto editUser(RegistrationRequest request) {
        UserDto editUserDto = null;
        if (repositary.existsByEmail(request.getUserEmail())) {
            Users user = repositary.findByEmail(request.getUserEmail());
            user.setName(request.getUserName()); 
            logger.debug("User " +user.getEmail()+" is edited");
            return editUserDto = createUserDto(repositary.save(user));
        }
       throw  new RuntimeException("User not edited couse user with email " + request.getUserEmail()+ " not found");
    }

    public UserDto createUserDto(Users user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getName());
        userDto.setUserEmail(user.getEmail());
        userDto.setOrganization(user.getOrg());
        return userDto;
    }

    public boolean changePassword(String pass, String email) {
        Users user = repositary.findByEmail(email);
        if (null != user) {
            user.setPassword(encoder.encode(pass));
            repositary.save(user);
            logger.debug("Password to user "+ user.getEmail()+ " changed");
            return true;
        }
       throw new RuntimeException("User with email " + email + " doesn't exists");
    }

    public Users createUser(RegistrationRequest request) {
        Set<Role> roles = new HashSet<>();
        roles.add(rolesServise.findRoleByName("USER"));
        roles.add(rolesServise.findRoleByName("ADMIN"));
        Users user = repositary.findByEmail(request.getUserEmail());
        if (user == null) {
            user = new Users();
            user.setName(request.getUserName());
            user.setEmail(request.getUserEmail());
            user.setPassword(encoder.encode(request.getUserPass()));
            user.setRoles(roles);             
            return user;
        }
         throw new RegistrationException("User with email " + request.getUserEmail() + " is already exists");
    }

    public boolean existsByEmail(String email) {
        return repositary.existsByEmail(email);
    }

    public Users getByName(String name) {
       return repositary.findByName(name);
    }
}
