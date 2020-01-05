package com.store.goodsstore.services;

import com.store.goodsstore.dto.OrganizationDto;
import com.store.goodsstore.repository.OrganizationRepository;
import com.store.goodsstore.dto.RegistrationRequest;
import com.store.goodsstore.dto.UserDto;
import com.store.goodsstore.entities.Organization;
import com.store.goodsstore.exceptions.RegistrationException;
import java.security.Principal;
import java.util.UUID;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Organization saveOrganisation(Organization organization) {
        return repository.save(organization);
    }

    public Organization createOrganization(RegistrationRequest request) {
        Organization organization = repository.findByEmail(request.getOrganizationEmail());
        if (null == organization) {
            organization = new Organization();
            organization.setName(request.getOrganizationName());
            organization.setEmail(request.getOrganizationEmail());
            organization.setCode(createIdentifier());
            logger.debug("Organisation "+ request.getOrganizationEmail()+ " created");
            return organization;
        }
        throw new RegistrationException("Organization with email " + request.getOrganizationEmail() + " is alredy exists");
    }

    public OrganizationDto createOrganizationResponse(Organization organization) {
        OrganizationDto organizationResponse = new OrganizationDto();
        organizationResponse.setOrganizationName(organization.getName());
        organizationResponse.setOrganizationEmail(organization.getEmail());
        organizationResponse.setOrgCode(organization.getCode());
        organizationResponse.setUserName(organization.getUsers().getName());
        organizationResponse.setUserEmail(organization.getUsers().getEmail());
        organizationResponse.setStorename(organization.getStore().getName());
        organizationResponse.setStoreCode(organization.getStore().getCode());
        organizationResponse.setDescription(organization.getStore().getDescription());
        return organizationResponse;
    }

    @Transactional(readOnly = true)
    public Organization getByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public Organization getByName(String name) {
        return repository.findByName(name);

    }

    public String createIdentifier() {
        String identifier = UUID.randomUUID().toString();
        return identifier;
    }

    @Transactional
    Organization editOrganization(RegistrationRequest request) {
        Organization organization = repository.findByEmail(request.getOrganizationEmail());
        organization.setName(request.getOrganizationName());
        organization.setEmail((request.getOrganizationEmail()));
        logger.debug("Organization "+ request.getOrganizationEmail()+" edited");
        return repository.save(organization);

    }

    public OrganizationDto getOrgData(Principal principal) {
        String name = principal.getName();
        UserDto user = userService.getUsersByEmail(name);
        Organization org = user.getOrganization();
        return createOrganizationResponse(org);
    }

    @Transactional(readOnly = true)
    public Organization getByCode(String orgCode) {
        return  repository.findByCode(orgCode);
    }

}
