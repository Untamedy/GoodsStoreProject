/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



/**
 *
 * @author Lenovo
 */
@Controller()
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    
    

    @GetMapping("/login")
    public String login(Model model) {
        return "loginPage";
    }
    
    
    
    @GetMapping("/logout")
    public String logout(){
        return "loginPage";
    }
    
}
