/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.entities;


import java.util.Set;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Lenovo
 */
@Data
public class User implements UserDetails {
    
   
    private Set<Role> authorities;
    private String password;
    private String username;
    private Organization organization;

    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
  
}
