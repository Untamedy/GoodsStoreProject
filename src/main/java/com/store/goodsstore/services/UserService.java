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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 *
 * @author YBolshakova
 */
@Service
public class UserService{

    @Autowired
    UserRepository repositary;   
    @Autowired
    RolesServise rolesServise;    
    @Autowired
    PasswordEncoder encoder;
    

    public UserDto getUsersByEmail(String email) {
        UserDto userDto = new UserDto();
        Users user = repositary.findByUserEmail(email);
        if (null != user) {
            return createUserRespons(user,State.EXISTING);            
        }else{
            userDto.setState(State.NOT_FOUND);
        }               
        
        return userDto;
    }

    public Page<UserDto> getAllUsers(int pages, int size) {  
        Pageable page = PageRequest.of(pages, size);                
        Page<UserDto> userList = repositary.findAll(page).map((u) -> {
            return createUserRespons(u, State.EXISTING); 
        });        
        return userList;
    }

    public UserDto saveUser(UserRequestDto userRequestDto) {        
        if (!isUserExist(userRequestDto.getUserEmail())) {
            Users user = createUser(userRequestDto);
            repositary.save(user);
           return createUserRespons(user,State.SAVED);  
        }
        throw new RuntimeException("User is already exists");
    }
    
    

    public Users createUser(UserRequestDto userRequestDto) {
        Users user = new Users();
        Set<Role> roles = new HashSet<>();
        user.setUsername(userRequestDto.getUsername());
        user.setPassword(userRequestDto.getPassword());
        user.setUserEmail(userRequestDto.getUserEmail());
        roles.add(rolesServise.findRoleByName("user"));
        user.setAuthorities(roles);        
        return user;
    }

    public boolean isUserExist(String email) {
        return repositary.findByUserEmail(email) != null;
    }
    
     public UserRequestDto createUserRequest(RegistrationRequest request, Organization organization) {
        UserRequestDto user = new UserRequestDto();
        user.setUsername(request.getUserName());
        user.setUserEmail(request.getUserEmail());
        user.setPassword(encoder.encode(request.getUserPass()));
        user.setOrganization(organization);      
        
        return user;
    }
     
      public UserDto createUserRespons(Users user, State state){
        int id = repositary.findByUserEmail(user.getUserEmail()).getId();
        String email = user.getUserEmail();
        UserDto userDto = new UserDto(); 
        userDto.setId(id);
        userDto.setUserEmail(email);
        userDto.setState(state); 
        userDto.setOrganization(user.getOrgatisation());
        return userDto;        
    }
   

        public boolean changePassword(String pass, String email) {
        Users user = repositary.findByUserEmail(email);
        if(null!=user){
            user.setPassword(pass);
            repositary.save(user);
            return true;
        }
        return  false;
    }

}
