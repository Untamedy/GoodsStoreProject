package com.store.goodsstore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author YBolshakova
 */
@Controller
public class StartController {
    
    @GetMapping("/")
    public String menu(){
        return "index";
    }

}
