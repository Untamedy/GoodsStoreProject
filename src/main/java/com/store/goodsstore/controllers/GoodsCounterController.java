/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.controllers;

import com.store.goodsstore.dto.GoodsCounterDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Lenovo
 */
@Controller("/goodsCount")
public class GoodsCounterController {
    
    @PostMapping("/updateCount")
    public ModelAndView addGoodsCount(@RequestBody GoodsCounterDto godsCounterDto){  
        
        
        return new ModelAndView();
        
    }
    
    
    
}
