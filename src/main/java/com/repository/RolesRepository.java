package com.repository;

import com.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author YBolshakova
 */
public interface RolesRepository extends JpaRepository<Role, Integer>{
    public Role findByRoleName(String name);
    public boolean existsByRoleName(String name);

}
