/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.store.goodsstore.services.StoreService;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Lenovo
 */
@Controller
public class StoreController {
    
   @Autowired
   private StoreService service;

    @GetMapping("/store")
    public ModelAndView toStorepage(@RequestParam("code") String code) {
        ModelAndView model = new ModelAndView("storePage");
        model.addObject("store", service.getByCode(code));       
        return model;

    }
}
