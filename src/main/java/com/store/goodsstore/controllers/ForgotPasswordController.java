package com.store.goodsstore.controllers;

import com.store.goodsstore.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    @GetMapping("/sendEmail")
    public ModelAndView sendEmail(){
       return new ModelAndView("sendEmailPage");
    }
    
    
     @PostMapping("/forgotPass")
    public ModelAndView restorePass(@RequestParam("email") String email){
        if(userService.existsByEmail(email)){
            return new ModelAndView("forgotPassFormPage","email",email);
        }
        return new ModelAndView("error");
    }
    
    @PostMapping("/restorePass")
    public ModelAndView setNewPass(@RequestParam("newPass") String pass,@RequestParam("email") String email){    
        userService.changePassword(pass,email);
            return new ModelAndView("loginPage");  
    }
}
