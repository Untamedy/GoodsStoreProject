package com.store.goodsstore.services;

import com.store.goodsstore.dto.OrganizationDto;
import com.store.goodsstore.dto.RegistrationRequest;
import com.store.goodsstore.dto.RegistrationResponse;
import com.store.goodsstore.dto.StoreDto;
import com.store.goodsstore.dto.UserDto;
import com.store.goodsstore.entities.Organization;
import com.store.goodsstore.entities.Store;
import com.store.goodsstore.entities.Users;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void  register(RegistrationRequest request) {
        Organization organization = organizationService.createOrganization(request);       
        Users user = userService.createUser(request,organization);
        Store store = storeService.createStore(request,organization); 
        organization.setUsers(user);
        organization.setStore(store);
        organizationService.saveOrganisation(organization);        
    }
    
    @Transactional
    public RegistrationResponse editRegData(RegistrationRequest request) {
        UserDto user = userService.editUser(request);
        StoreDto store = storeService.editStore(request);
        Organization organization = organizationService.editOrganization(request);
        OrganizationDto editOrganization = organizationService.createOrganizationResponse(organization);
        return new RegistrationResponse(editOrganization);
    }
    
   

    public RegistrationRequest createRequest(Map<String,String> quaryMap) {
        RegistrationRequest request = new RegistrationRequest();
        request.setOrganizationName(quaryMap.get("orgName"));
        request.setOrganizationEmail(quaryMap.get("orgEmail"));
        request.setStoreName(quaryMap.get("storeName"));
        request.setUserName(quaryMap.get("userName"));
        request.setUserEmail(quaryMap.get("userEmail"));
        request.setUserPass(quaryMap.get("password"));
        return request;
    }

}
