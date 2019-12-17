package com.store.goodsstore.repository;

import com.store.goodsstore.entities.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author YBolshakova
 */
public interface OrganizationRepository extends JpaRepository<Organization, Integer>{
    
    public boolean existsByEmail(String email);
    public Organization findByEmail(String email);
    public Organization findByName(String name);

    public Organization findByCode(String orgCode);
    

}
