package com.store.goodsstore.controllers;

import com.store.goodsstore.dto.RegistrationRequest;
import com.store.goodsstore.dto.RegistrationResponse;
import com.store.goodsstore.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
public class AuthorizationController {
    
    @Autowired
    RegistrationService registrationService;
    
    @GetMapping("/signup")
    public String forvardToSignupPage(){
        return "signUpPage";
                
    }
    
    @PostMapping("/registration")
    public ResponseEntity registration(){
        return null;
        
        
        
    }
}
            
            
    
    
    
 @RequestMapping(method = RequestMethod.POST, value = "/signup")
 public ResponseEntity<RegistrationResponse> singUp(@RequestBody RegistrationRequest request){     
     return new ResponseEntity<>(registrationService.register(request), HttpStatus.OK);
 }
 
 
    

}
