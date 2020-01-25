package com.store.goodsstore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author YBolshakova
 */
@Controller
public class ErrorController {
    
    @GetMapping("/gostore/error")
    public ModelAndView getEror(){
        return new ModelAndView("error");
    }
    
}
