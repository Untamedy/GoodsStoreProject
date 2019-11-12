package com.store.goodsstore.services;


import com.store.goodsstore.dto.RegistrationRequest;
import com.store.goodsstore.persistent.State;
import com.store.goodsstore.dto.UserRequest;
import com.store.goodsstore.dto.UserResponse;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;

/**
 *
 * @author YBolshakova
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository repositary;   
    @Autowired
    RolesServise rolesServise;    
    @Autowired
    PasswordEncoder encoder;
    

    public UserResponse getUsersByEmail(String email) {
        UserResponse userResponsDto = new UserResponse();
        Users user = repositary.findByUserEmail(email);
        if (null != user) {
            userResponsDto = createUserRespons(user,State.EXISTING);
            return userResponsDto;
        }else{
            userResponsDto.setState(State.NOT_FOUND);
        }               
        
        return userResponsDto;
    }

    public Page<UserResponse> getAllUsers(int pages, int size) {  
        Pageable page = PageRequest.of(pages, size);                
        Page<UserResponse> userList = repositary.findAll(page).map((u) -> {
            return createUserRespons(u, State.EXISTING); 
        });        
        return userList;
    }

    public UserResponse saveUser(UserRequest userRequestDto) {        
        if (!isUserExist(userRequestDto.getUserEmail())) {
            Users user = createUser(userRequestDto);
            repositary.save(user);
            UserResponse userResponseDto = createUserRespons(user,State.SAVED);            
            return userResponseDto;
        }
        throw new RuntimeException("User is already exists");
    }
    
    

    public Users createUser(UserRequest userRequestDto) {
        Users user = new Users();
        Set<Role> roles = new HashSet<>();
        user.setUsername(userRequestDto.getUserName());
        user.setPassword(userRequestDto.getPassword());
        user.setUserEmail(userRequestDto.getUserEmail());
        roles.add(rolesServise.findRoleByName("admin"));
        user.setAuthorities(roles);        
        return user;
    }

    public boolean isUserExist(String email) {
        return repositary.findByUserEmail(email) != null;
    }
    
     public UserRequest createUserRequest(RegistrationRequest request, Organization organization) {
        UserRequest user = new UserRequest();
        user.setUserName(request.getUserName());
        user.setUserEmail(request.getUserEmail());
        user.setPassword(encoder.encode(request.getUserPass()));
        user.setOrganization(organization);
        return user;
    }
     
      public UserResponse createUserRespons(Users user, State state){
        int id = repositary.findByUserEmail(user.getUserEmail()).getId();
        String email = user.getUserEmail();
        UserResponse userDto = new UserResponse(); 
        userDto.setId(id);
        userDto.setUserEmail(email);
        userDto.setState(state); 
        userDto.setOrganization(user.getOrgatisation());
        return userDto;        
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = repositary.findByUserEmail(email);  
        if(null!=user){
            return user;
        }
        throw new UsernameNotFoundException("user " + email + " not found");
               
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
