package com.controllers;

import com.entities.Role;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.repository.RolesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author YBolshakova
 */
@RestController
public class RolesController {
    
    @Autowired
    private RolesRepository repositary;

    @RequestMapping(method = RequestMethod.GET, value = "/role")
    public ResponseEntity<List<Role>> getAllRoles(){
       List<Role> allRoles = repositary.findAll();
       return new ResponseEntity<>(allRoles, HttpStatus.OK);
    }
}
