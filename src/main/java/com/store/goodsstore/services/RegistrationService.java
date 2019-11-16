package com.store.goodsstore.services;

import com.store.goodsstore.dto.OrganizationDto;
import com.store.goodsstore.dto.RegistrationRequest;
import com.store.goodsstore.dto.RegistrationResponse;
import com.store.goodsstore.dto.StoreDto;
import com.store.goodsstore.dto.UserDto;
import com.store.goodsstore.dto.UserRequestDto;
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
        RegistrationResponse registrationResponse = null;
        OrganizationDto organization = organizationService.saveOrganisation(request); 
        UserRequestDto userRequest = userService.createUserRegRequest(request, organizationService.createOrganization(request));        
        UserDto user = userService.saveUser(userRequest);  
        StoreDto storeRequest = storeService.createStoreRegistrRequest(request,organizationService.createOrganization(request));
        StoreDto store = storeService.saveStore(storeRequest);        
        if(organization!=null&&user!=null&&store!=null){
            registrationResponse = new RegistrationResponse(user,organization,store);                   
        }
        return  registrationResponse;
    }
    

}
