package com.services;

import com.config.RolesInit;
import com.entities.Role;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.repository.RolesRepository;

/**
 *
 * @author YBolshakova
 */
@Service

public class RolesServise {
    
@Autowired
private RolesInit rolesInit;
@Autowired
private RolesRepository repositary;

public Role findRoleByName(String name){    
    return repositary.findByRoleName(name);    
}



}
