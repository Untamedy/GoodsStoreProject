package com.controllers;

import com.dto.RegistrationRequest;
import com.dto.RegistrationResponse;
import com.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author YBolshakova
 */
@RestController
@RequestMapping("/authorization")
public class AuthorizationController {
    
    @Autowired
    RegistrationService registrationService;
    
 @RequestMapping(method = RequestMethod.POST, value = "/singup")
 public ResponseEntity<RegistrationResponse> singUp(@RequestBody RegistrationRequest request){     
     return new ResponseEntity<>(registrationService.register(request), HttpStatus.OK);
 }
 
 
    

}
