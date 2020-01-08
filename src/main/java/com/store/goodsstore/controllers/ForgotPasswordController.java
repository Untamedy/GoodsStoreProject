package com.store.goodsstore.controllers;

import com.store.goodsstore.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author YBolshakova
 */
@Controller
public class ForgotPasswordController {
    private static final Logger logger = LoggerFactory.getLogger(ForgotPasswordController.class);
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/forgotpass")
    public String returnForm(){
        return "forgotPassForm";
    }
    
     @GetMapping("/forgotPass")
    public ModelAndView restorePass(@RequestParam("userEmai") String email){
        if(userService.existsByEmail(email)){
            return new ModelAndView("forgotPassFormPage","email",email);
        }
        return new ModelAndView("error");
    }
    
    @GetMapping("/restorePass/{newPass}")
    public ModelAndView setNewPass(@RequestParam("newPass") String pass,@RequestParam("email") String email){    
        userService.changePassword(pass,email);
            return new ModelAndView("loginPage");  
    }
}
