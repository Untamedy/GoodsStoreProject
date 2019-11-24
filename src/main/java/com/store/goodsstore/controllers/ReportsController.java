/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.controllers;

import com.store.goodsstore.services.IncomDocService;
import com.store.goodsstore.services.OrderService;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    @PostMapping("/saleGoodsReport/{docNum}")
    public ModelAndView createSaleReport(@PathVariable ("docNum") String docNum){
        return new ModelAndView("incodeDoc", "incomeDoc", orderService.getByNum(docNum));        
    }
    
   
    
    @PostMapping("/incomeGoodsReport/{docNum}")
    public ModelAndView createAddGoodsCountrepost(@PathVariable ("docNum") String docNum){
       return new ModelAndView("incodeDoc", "incomeDoc", incomeService.getByNum(docNum));
    } 
    
    @PostMapping("/finReport/{dateFrom}/{dateTo}")
    public ModelAndView createFinReport(@PathVariable("dateFrom") Date dateFrom, @PathVariable("dateTo")Date dateTo){
        ModelAndView model = new ModelAndView("finReportPage");        
        model.addObject("orders", orderService.getByPeriod(dateFrom,dateTo));
        model.addObject("incomeDOc", incomeService.getByPeriod(dateFrom,dateTo));
        return model;        
    }
    
    
    
    
}
