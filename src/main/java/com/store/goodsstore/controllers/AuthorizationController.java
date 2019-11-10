package com.store.goodsstore.controllers;

import com.store.goodsstore.dto.RegistrationRequest;
import com.store.goodsstore.dto.RegistrationResponse;
import com.store.goodsstore.services.RegistrationService;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author YBolshakova
 */

@RestController
public class AuthorizationController {
    
    @Autowired
    RegistrationService registrationService;
    
    @GetMapping("/signup")
    public String forvardToSignupPage(){
        return "signUpPage";
                
    }
    
    @PostMapping("/registration")
    public ResponseEntity registration(@RequestBody RegistrationRequest registrationRequest){
        RegistrationResponse response = registrationService.register(registrationRequest);
        if(null!=response){        
                HttpHeaders headers = new HttpHeaders();
                headers.add("Location", "/login");
                return new ResponseEntity<>(registrationService.register(registrationRequest),headers, HttpStatus.OK);           
        }   
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }         
            
 
 
 
    

}
