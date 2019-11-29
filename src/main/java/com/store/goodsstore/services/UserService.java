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
import java.util.List;
import java.util.stream.Collectors;
;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author YBolshakova
 */


@Service
public class UserService {

    @Autowired
    UserRepository repositary;
    @Autowired
    RolesServise rolesServise;
    @Autowired
    PasswordEncoder encoder;
    
    

    public UserDto getUsersByEmail(String email) {
        UserDto userDto = null;
        Users user = repositary.findByEmail(email);
        if (null != user) {
            return userDto = createUserRespons(user);
        }        
        return userDto;
    }

    public List<UserDto> getAllUsers() {
        List<UserDto> userList = repositary.findAll().stream().map(user -> {
            UserDto userDto = createUserRespons(user);
            return userDto;
        }).collect(Collectors.toList());
        return userList;
    }

    public UserDto editUser(RegistrationRequest request) {
        UserDto editUserDto = null;
        if (repositary.existsByEmail(request.getUserEmail())) {
            Users user = repositary.findByEmail(request.getUserEmail());
            user.setName(request.getUserName());           
            return editUserDto = createUserRespons(repositary.save(user));
        }
        return editUserDto;
    }

    public UserDto createUserRespons(Users user) {
        UserDto userDto = new UserDto();
        userDto.setUserEmail(user.getName());
        userDto.setUserEmail(user.getEmail());
        return userDto;
    }

    public boolean changePassword(String pass, String email) {
        Users user = repositary.findByEmail(email);
        if (null != user) {
            user.setPassword(pass);
            repositary.save(user);
            return true;
        }
        return false;
    }

    public Users saveNewUser(RegistrationRequest request) {
        Set<Role> roles = new HashSet<>();
        roles.add(rolesServise.findRoleByName("user"));
        roles.add(rolesServise.findRoleByName("admin"));
        Users user = repositary.findByEmail(request.getUserEmail());
        if (user == null) {
            user = new Users();
            user.setName(request.getUserName());
            user.setEmail(request.getUserEmail());
            user.setPassword(request.getUserPass());
            user.setRoles(roles); 
            return repositary.save(user);
        }
         throw new RegistrationException("User with email " + request.getUserEmail() + " is already exists");
    }

    public boolean existsByEmail(String email) {
        return repositary.existsByEmail(email);
    }
}
