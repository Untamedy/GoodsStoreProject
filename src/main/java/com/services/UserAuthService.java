/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.entities.Users;



/**
 *
 * @author YBolshakova
 */
public interface UserAuthService {
    Users getUser(String login);
    
}
