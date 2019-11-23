/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.controllers;

import com.store.goodsstore.dto.GoodsPageRequest;
import com.store.goodsstore.services.IncomDocService;
import com.store.goodsstore.services.OrderService;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Lenovo
 */
@Controller("/reports")
public class ReportsController {
    
    private static final Logger logger = LoggerFactory.getLogger(ReportsController.class);
    
   @Autowired
   private OrderService orderService;
   
   @Autowired
   private IncomDocService incomeService;
    
    @PostMapping("/saleGoodsReport/{dateFrom}/{dateTo}")
    public ModelAndView createSaleReport(@PathVariable ("dateFrom") Date dateFrom, @PathVariable("dateTo") Date dateTo){
       
        
        return null;        
    }
    
   
    
    @PostMapping("/incomeGoodsReport/{dateFrom}/{dateTo}")
    public ModelAndView createAddGoodsCountrepost(@RequestBody GoodsPageRequest request){
        return null;        
    } 
    
    @PostMapping("/finReport/{dateFrom}/{dateTo}")
    public ModelAndView createFinReport(){
        return null;        
    }
    
    
    
    
}
