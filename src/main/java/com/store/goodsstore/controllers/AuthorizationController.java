package com.store.goodsstore.controllers;

import com.store.goodsstore.dto.RegistrationRequest;
import com.store.goodsstore.services.RegistrationService;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ModelAndView registration(@RequestParam(value = "organization") Map<String, String> queryMap) {
        logger.debug("Received a request for registration  user ");
        RegistrationRequest request = registrationService.createRequest(queryMap);
        registrationService.register(request);
        logger.debug("Registered success");
        return new ModelAndView("/loginPage", HttpStatus.CREATED);
    }

}
