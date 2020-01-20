package com.store.goodsstore.services;

import com.store.goodsstore.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.store.goodsstore.repository.RolesRepository;

/**
 *
 * @author YBolshakova
 */
@Service
public class RolesService {

    
    @Autowired
    private RolesRepository repositary;

    public Role findRoleByName(String name) {
        return repositary.findByRoleName(name);
    }

}
