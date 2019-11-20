package com.store.goodsstore.services;

import com.store.goodsstore.dto.OrganizationDto;
import com.store.goodsstore.dto.RegistrationRequest;
import com.store.goodsstore.dto.RegistrationResponse;
import com.store.goodsstore.entities.Organization;
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
        OrganizationDto organizationDto=null;
        Organization organization = organizationService.createOrganization(request);
        if(organization!=null){
             organizationDto = organizationService.saveOrganisation(request); 
             registrationResponse =  new RegistrationResponse(organizationDto);
        }          
        return registrationResponse;
    }
    

}
