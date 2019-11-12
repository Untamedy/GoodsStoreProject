/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.controllers;

import com.store.goodsstore.repository.UserRepository;
import com.store.goodsstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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
    
    @PostMapping("/forgotPass{:userEmail}")
    public ModelAndView restorePass(@PathVariable("emai") String email){
        if(userServie.isUserExist(email)){
            return new ModelAndView("restorePassPage","email",email);
        }
        return new ModelAndView("errorPage", HttpStatus.BAD_REQUEST);
    }
    
    @PostMapping("/restorePass{:newPass}")
    public ModelAndView setNewPass(@PathVariable("newPass") String pass,Model model){
        String email = (String) model.getAttribute("email");
        if(userServie.changePassword(pass,email)){
            return new ModelAndView("loginPage","msg","Success");
        }
        return new ModelAndView("errorPage", HttpStatus.BAD_REQUEST);
        
        
    }
    
    

}
