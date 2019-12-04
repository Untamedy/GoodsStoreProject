package com.store.goodsstore.services;

import com.store.goodsstore.dto.OrganizationDto;
import com.store.goodsstore.repository.OrganizationRepository;
import com.store.goodsstore.dto.RegistrationRequest;
import com.store.goodsstore.entities.Organization;
import com.store.goodsstore.entities.Users;
import com.store.goodsstore.exceptions.RegistrationException;
import java.util.UUID;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author YBolshakova
 */
@Service
public class OrganizationService {

    private static final Logger logger = Logger.getLogger(OrganizationService.class.getName());

    @Autowired
    private UserService userService;

    @Autowired
    private StoreService storeService;

    @Autowired
    OrganizationRepository repository;

    public OrganizationDto saveOrganisation(Organization organization) {
        return createOrganizationResponse(repository.save(organization));
    }

    public Organization createOrganization(RegistrationRequest request) {
        Organization organization = repository.findByEmail(request.getOrganizationEmail());
        if (null == organization) {
            organization = new Organization();
            organization.setName(request.getOrganizationName());
            organization.setEmail(request.getOrganizationEmail());
            organization.setCode(createIdentifier());
            return organization;
        } 
            throw new RegistrationException("Organization with email " + request.getOrganizationEmail() + " is alredy exists");        
    }

    public OrganizationDto createOrganizationResponse(Organization organization) {
        OrganizationDto organizationResponse = new OrganizationDto();
        organizationResponse.setOrganizationId(organization.getId());
        organizationResponse.setOrganizationName(organization.getName());
        organizationResponse.setOrganizationEmail(organization.getEmail());
        organizationResponse.setUserName(organization.getUsers().getName());
        organizationResponse.setUserEmail(organization.getUsers().getEmail());
        organizationResponse.setStorename(organization.getStore().get(0).getName());
        organizationResponse.setDescription(organization.getStore().get(0).getDescription());
        return organizationResponse;
    }

    public Organization getByEmail(String email) {
        return repository.findByEmail(email);
    }

    public Organization getByName(String name) {
        return repository.findByName(name);

    }

    public String createIdentifier() {
        String identifier = UUID.randomUUID().toString();
        return identifier;
    }

    Organization editOrganization(RegistrationRequest request) {
        Organization organization = repository.findByEmail(request.getOrganizationEmail());
        organization.setName(request.getOrganizationName());
        return repository.save(organization);

    }
    
   

}
