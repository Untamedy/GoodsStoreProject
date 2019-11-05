package com.services;

import com.dto.OrganizationResponse;
import com.dto.RegistrationRequest;
import com.dto.RegistrationResponse;
import com.dto.StoreRequest;
import com.dto.StoreResponse;
import com.dto.UserRequest;
import com.dto.UserResponse;
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
        OrganizationResponse organization = organizationService.saveOrganisation(request); 
        UserRequest userRequest = userService.createUserRequest(request, organizationService.createOrganization(request));
        UserResponse user = userService.saveUser(userRequest);  
        StoreRequest storeRequest = storeService.createStoreRequest(request,organizationService.createOrganization(request));
        StoreResponse store = storeService.saveStore(storeRequest);
        RegistrationResponse registrationResponse = new RegistrationResponse(user, organization,store);
        return  registrationResponse;
    }
    

}
