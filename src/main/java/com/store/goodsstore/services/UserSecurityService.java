/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.services;

import com.store.goodsstore.entities.Users;
import com.store.goodsstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lenovo
 */
@Service
public class UserSecurityService implements UserDetailsService {
    
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       Users dbUser = repository.findByEmail(email);  
        if(null!=dbUser){
       UserDetails user = new User(dbUser.getUserEmail(), dbUser.getPassword(), dbUser.getRoles());       
            return user;
        }
        throw new UsernameNotFoundException("user " + email + " not found");
    }
    
}
