package com.store.goodsstore.controllers;

import com.store.goodsstore.dto.RegistrationRequest;
import com.store.goodsstore.dto.RegistrationResponse;
import com.store.goodsstore.services.RegistrationService;
import com.sun.corba.se.spi.presentation.rmi.StubAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author YBolshakova
 */

@Controller
public class AuthorizationController {
    
    @Autowired
    RegistrationService registrationService;
    
    @GetMapping("/signup")
    public String forvardToSignupPage(){
        return "signUpPage";
                
    }
    
    @PostMapping("/registration")
    public ModelAndView registration(@RequestParam RegistrationRequest request){ 
        RegistrationResponse response = registrationService.register(request);
        if(null!=response){   
                return new ModelAndView("/loginPage", HttpStatus.CREATED);       
        }   
        return new ModelAndView("signUpPage", "request", request);
                
    }         
            
 
 
 
    

}
