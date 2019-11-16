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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author YBolshakova
 */
@Controller
public class ForgotPassword {
    private static final Logger logger = LoggerFactory.getLogger(RolesController.class);
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/forgotpass")
    public String returnForm(){
        return "forgotPassForm";
    }
    
     @PostMapping("/forgotPass{:userEmail}")
    public ModelAndView restorePass(@PathVariable("emai") String email){
        if(userService.isUserExist(email)){
            return new ModelAndView("restorePassPage","email",email);
        }
        return new ModelAndView("errorPage", HttpStatus.BAD_REQUEST);
    }
    
    @PostMapping("/restorePass{:newPass}")
    public ModelAndView setNewPass(@PathVariable("newPass") String pass,Model model){
        String email = (String) model.getAttribute("email");
        if(userService.changePassword(pass,email)){
            return new ModelAndView("loginPage","msg","Success");
        }
        return new ModelAndView("errorPage", HttpStatus.BAD_REQUEST);
        
        
    }

}
