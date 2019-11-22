package com.store.goodsstore.services;

import com.store.goodsstore.dto.RegistrationRequest;
import com.store.goodsstore.dto.UserDto;
import com.store.goodsstore.entities.Role;
import com.store.goodsstore.entities.Users;
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

    public UserDto editUser(UserDto userDto) {
        UserDto editUserDto = null;
        if (repositary.existsByEmail(userDto.getUserEmail())) {
            Users user = repositary.findByEmail(userDto.getUserEmail());
            user.setName(userDto.getUsername());
            user.setEmail(userDto.getUserEmail());
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

    public Users createUser(RegistrationRequest request) {
        Set<Role> roles = new HashSet<>();
        roles.add(rolesServise.findRoleByName("user"));
        roles.add(rolesServise.findRoleByName("admin"));

        Users user = repositary.findByEmail(request.getUserEmail());
        if (user != null) {
            user = new Users();
            user.setName(request.getUserName());
            user.setEmail(request.getUserEmail());
            user.setPassword(request.getUserPass());
            user.setRoles(roles);
        }
        return user;
    }

    public boolean existsByEmail(String email) {
        return repositary.existsByEmail(email);
    }
}
