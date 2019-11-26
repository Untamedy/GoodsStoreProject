package com.store.goodsstore.services;

import com.store.goodsstore.dto.OrganizationDto;
import com.store.goodsstore.dto.RegistrationRequest;
import com.store.goodsstore.dto.RegistrationResponse;
import com.store.goodsstore.dto.StoreDto;
import com.store.goodsstore.dto.UserDto;
import com.store.goodsstore.entities.Organization;
import com.store.goodsstore.entities.Store;
import com.store.goodsstore.entities.Users;
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
    public void register(RegistrationRequest request) {
        Organization organization = organizationService.createOrganization(request);
        Users user = userService.saveNewUser(request);
        Store store = storeService.saveStore(request);
        organization.setUser(user);
        organization.setStore(store);
        organizationService.saveOrganisation(organization);
    }

    public RegistrationResponse editRegData(RegistrationRequest request) {
        UserDto user = userService.editUser(request);
        StoreDto store = storeService.editStore(request);
        Organization organization = organizationService.editOrganization(request);
        OrganizationDto editOrganization = organizationService.createOrganizationResponse(organization);
        return new RegistrationResponse(editOrganization);
    }

    public RegistrationRequest createRequest(String[] orgData) {
        RegistrationRequest request = new RegistrationRequest();
        request.setOrganizationName(orgData[0]);
        request.setOrganizationEmail(orgData[1]);
        request.setStoreName(orgData[2]);
        request.setUserName(orgData[3]);
        request.setUserEmail(orgData[4]);
        request.setUserPass(orgData[5]);
        return request;
    }

}
