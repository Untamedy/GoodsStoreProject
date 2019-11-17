package com.store.goodsstore.controllers;


import com.store.goodsstore.dto.UserDto;
import com.store.goodsstore.dto.UserRequestDto;
import com.store.goodsstore.services.UserService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


/**
 *
 * @author YBolshakova
 */
@Controller("/admin/user")
public class UserController {     
    
    
    @Autowired
    UserService userService;

    @PostMapping("/editUser")
    public ModelAndView saveUser(@RequestBody UserDto userDto) {         
        return new ModelAndView("userPage", "editUser", userService.editUser(userDto));
    }

    @GetMapping("/users")
    public ModelAndView getAllUser(Map<String,UserDto>model) { 
        List<UserDto> users = userService.getAllUsers();
        model = users.stream().collect(Collectors.toMap(UserDto::getUsername,u->u));
        return new ModelAndView("userListPage",model, HttpStatus.OK);   
    }
    
    @GetMapping("/thisUser?email=email")
    public ModelAndView getUserByEmail(@RequestParam("email") String email){  
        UserDto user = userService.getUsersByEmail(email);       
        return new ModelAndView("userPage", "user", user);        
    }
}
