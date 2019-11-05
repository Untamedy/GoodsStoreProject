package com.repository;

import com.entities.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author YBolshakova
 */
public interface OrganizationRepository extends JpaRepository<Organization, Integer>{
    
    public boolean existsByOrganizationEmail(String email);
    public Organization findByOrganizationEmail(String email);
    

}
