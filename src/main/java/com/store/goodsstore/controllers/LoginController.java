/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.controllers;

import com.store.goodsstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 *
 * @author Lenovo
 */
@Controller()
public class LoginController {
    
    @Autowired
    private UserService userServie;

    @GetMapping("/login")
    public String login() {
        return "loginPage";
    }
    
    @GetMapping("/logout")
    public String logout(){
        return "startPage";
    }
    
}
