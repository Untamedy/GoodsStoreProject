package com.store.goodsstore.services;

import com.store.goodsstore.dto.OrganizationDto;
import com.store.goodsstore.repository.OrganizationRepository;
import com.store.goodsstore.dto.RegistrationRequest;
import com.store.goodsstore.entities.Organization;
import java.util.Optional;
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
    OrganizationRepository repository;

    public OrganizationDto saveOrganisation(RegistrationRequest request) {
        Organization organization = repository.findByOrganizationEmail(request.getOrganizationEmail());
        if (null == organization) {
            return createOrganizationResponse(repository.save(createOrganization(request)));
        }
        return null;
    }

    public Organization createOrganization(RegistrationRequest request) {
        Organization organization = new Organization();
        organization.setName(request.getOrganizationName());
        organization.setOrganizationEmail(request.getOrganizationEmail());
        organization.setIdentificationCode(createIdentifier());
        return organization;
    }

    public OrganizationDto createOrganizationResponse(Organization organization) {
        OrganizationDto organizationResponse = new OrganizationDto();
        organizationResponse.setOrganizationId(organization.getId());
        organizationResponse.setOrganizationName(organization.getName());
        organizationResponse.setOrganizationEmail(organization.getOrganizationEmail());
        return organizationResponse;
    }

    public String createIdentifier() {
        String identifier = UUID.randomUUID().toString();
        return identifier;
    }

}
