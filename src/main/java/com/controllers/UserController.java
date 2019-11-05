package com.controllers;

import com.dto.UserRequest;
import com.dto.UserResponse;
import com.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author YBolshakova
 */
@RestController
@RequestMapping("/user")
public class UserController {     
    
    
    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public ResponseEntity<UserResponse> saveUser(@RequestBody UserRequest user) {        
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users?pages={pages}&size={size}")
    public ResponseEntity<Page<UserResponse>> getAllUser(@PathVariable("pages") int pages, @PathVariable("size") int size) { 
        return new ResponseEntity<>(userService.getAllUsers(pages, size), HttpStatus.OK);   
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/thisUser/{email}")
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable("email") String email){        
        return new ResponseEntity<>(userService.getUsersByEmail(email), HttpStatus.OK);        
    }
}
