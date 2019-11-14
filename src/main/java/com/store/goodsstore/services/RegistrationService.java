package com.store.goodsstore.services;

import com.store.goodsstore.dto.OrganizationDto;
import com.store.goodsstore.dto.RegistrationRequest;
import com.store.goodsstore.dto.RegistrationResponse;
import com.store.goodsstore.dto.StoreRequest;
import com.store.goodsstore.dto.StoreDto;
import com.store.goodsstore.dto.UserRequest;
import com.store.goodsstore.dto.UserResponse;
import com.store.goodsstore.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author YBolshakova
 */
@Service
public class RegistrationService {

    @Autowired
    UserService userService;
    @Autowired
    OrganizationService organizationService;
    @Autowired
    StoreService storeService;
        

    public RegistrationResponse register(RegistrationRequest request) { 
        OrganizationDto organization = organizationService.saveOrganisation(request); 
        UserRequest userRequest = userService.createUserRequest(request, organizationService.createOrganization(request));
        userRequest.getRoles().add(new Role("admin"));
        UserResponse user = userService.saveUser(userRequest);  
        StoreRequest storeRequest = storeService.createStoreRequest(request,organizationService.createOrganization(request));
        StoreDto store = storeService.saveStore(storeRequest);
        RegistrationResponse registrationResponse = new RegistrationResponse(user, organization,store);
        return  registrationResponse;
    }
    

}
