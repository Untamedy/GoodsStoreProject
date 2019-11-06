package com.services;


import com.dto.RegistrationRequest;
import com.persistent.State;
import com.dto.UserRequest;
import com.dto.UserResponse;
import com.entities.Organization;
import com.entities.Role;
import com.entities.Users;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

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
        user.setUserName(userRequestDto.getUserName());
        user.setPassword(userRequestDto.getPassword());
        user.setUserEmail(userRequestDto.getUserEmail());
        roles.add(rolesServise.findRoleByName("admin"));
        user.setRoles(roles);        
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
        Users user = repositary.existsByUserEmail(email);
        Set<GrantedAuthority> roles = new HashSet<>();
        if(user.getRoles().contains("admin")){
            roles.add(new SimpleGrantedAuthority("admin"));
            roles.add(new SimpleGrantedAuthority("user"));
        }else{
           roles.add(new SimpleGrantedAuthority("user"));
        }
        
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getPassword(), roles);
        
        return userDetails;
       
    }

}
