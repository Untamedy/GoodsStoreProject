package com.store.goodsstore.services;

import com.store.goodsstore.dto.OrganizationDto;
import com.store.goodsstore.dto.RegistrationRequest;
import com.store.goodsstore.dto.StoreDto;
import com.store.goodsstore.dto.UserDto;
import com.store.goodsstore.entities.Organization;
import com.store.goodsstore.entities.Store;
import com.store.goodsstore.entities.Users;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author YBolshakova
 */
@Service
public class RegistrationService {
     private static final Logger logger = LoggerFactory.getLogger(RegistrationService.class);

    @Autowired
    UserService userService;
    @Autowired
    OrganizationService organizationService;
    @Autowired
    StoreService storeService;

    @Transactional
    public OrganizationDto register(RegistrationRequest request) {
        Organization organization = organizationService.createOrganization(request);
        Users user = userService.createUser(request);
        Store store = storeService.createStore(request);
        organization.addUser(user);
        organization.addStore(store);
        logger.debug("New organisation with name "+request.getOrganizationName()+" registered");
        return organizationService.createOrganizationResponse(organizationService.saveOrganisation(organization));
    }

    @Transactional
    public OrganizationDto editRegData(RegistrationRequest request) {
        UserDto user = userService.editUser(request);
        StoreDto store = storeService.editStore(request);
        Organization organization = organizationService.editOrganization(request);
        logger.debug("Organization data edited");
        return organizationService.createOrganizationResponse(organization);       
    }

    public RegistrationRequest createRequest(Map<String, String> quaryMap) {
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
