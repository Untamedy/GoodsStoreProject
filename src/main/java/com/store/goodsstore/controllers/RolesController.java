package com.store.goodsstore.controllers;

import com.store.goodsstore.entities.Role;
import com.store.goodsstore.init.AcountInit;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.store.goodsstore.repository.RolesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author YBolshakova
 */
@RestController("/admin/roles")
public class RolesController {
    private static final Logger logger = LoggerFactory.getLogger(RolesController.class);
    
    @Autowired
    private RolesRepository repositary;

    @RequestMapping(method = RequestMethod.GET, value = "/role")
    public ResponseEntity<List<Role>> getAllRoles(){
       List<Role> allRoles = repositary.findAll();
       return new ResponseEntity<>(allRoles, HttpStatus.OK);
    }
}
