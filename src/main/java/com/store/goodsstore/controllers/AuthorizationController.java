package com.store.goodsstore.controllers;

import com.store.goodsstore.dto.OrganizationDto;
import com.store.goodsstore.dto.RegistrationRequest;
import com.store.goodsstore.services.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author YBolshakova
 */
@Controller
public class AuthorizationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationController.class);

    @Autowired
    RegistrationService registrationService;

    @GetMapping("/signup")
    public String forvardToSignupPage() {
        return "signupPage";

    }

    @PostMapping("/registration")
    public ModelAndView registration(@ModelAttribute("regForm") RegistrationRequest regrequest) {
        logger.debug("Received a request for registration  user "); 
        ModelAndView model = new ModelAndView("successPage");
        OrganizationDto dto = registrationService.register(regrequest);         
        model.addObject("orgDto",dto);
        logger.debug("Registered success");
        return model;
    }

}
