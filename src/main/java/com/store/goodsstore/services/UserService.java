package com.store.goodsstore.services;

import com.store.goodsstore.dto.RegistrationRequest;
import com.store.goodsstore.dto.UserDto;
import com.store.goodsstore.dto.UserRequestDto;
import com.store.goodsstore.persistent.State;
import com.store.goodsstore.entities.Organization;
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
        Users user = repositary.findByUserEmail(email);
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
        if (repositary.existsByUserEmail(userDto.getUserEmail())) {
            Users user = repositary.findByUserEmail(userDto.getUserEmail());
            user.setUsername(userDto.getUsername());
            user.setUserEmail(userDto.getUserEmail());
            return editUserDto = createUserRespons(repositary.save(user));
        }
        return editUserDto;
    }

    public UserDto createUserRespons(Users user) {
        UserDto userDto = new UserDto();
        userDto.setUserEmail(user.getUsername());
        userDto.setUserEmail(user.getUserEmail());
        return userDto;
    }

    public boolean changePassword(String pass, String email) {
        Users user = repositary.findByUserEmail(email);
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
        Users user = new Users();
        user.setUsername(request.getUserName());
        user.setUserEmail(request.getUserEmail());
        user.setPassword(request.getUserPass());
        user.setRoles(roles);
        return user;

    }
}
