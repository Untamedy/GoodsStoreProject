package com.store.goodsstore.services;

import com.store.goodsstore.init.RolesInit;
import com.store.goodsstore.entities.Role;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.store.goodsstore.repository.RolesRepository;

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
