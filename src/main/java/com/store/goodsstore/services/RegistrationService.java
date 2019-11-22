package com.store.goodsstore.services;

import com.store.goodsstore.dto.OrganizationDto;
import com.store.goodsstore.dto.RegistrationRequest;
import com.store.goodsstore.dto.RegistrationResponse;
import com.store.goodsstore.entities.Organization;
import com.store.goodsstore.entities.Store;
import com.store.goodsstore.entities.Users;
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

    public String register(RegistrationRequest request) {
        String message = "";
        
        Organization organization = organizationService.createOrganization(request);
        Users user = userService.createUser(request);
        Store store = storeService.createStore(request);
        if (organization != null && user != null && store != null) {
            organization.setUser(user);
            organization.setStore(store);
            organizationService.saveOrganisation(request);
            message = "Success";
           }else{
            if(organization==null){
                message = "Organisation already exists";
            }
            if(user==null){
                message = "User is already exists";                
            }
            if(store==null){
                message= "Store is already exists";
            }
        }
        return message;
    }

}
