/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.controllers;

import com.store.goodsstore.dto.GoodsPageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    
    private static final Logger logger = LoggerFactory.getLogger(RolesController.class);
    
    @PostMapping("/saleGoodsReport")
    public ModelAndView createSaleReport(){
        return null;        
    }
    
    @PostMapping("/sumGoodsCountReports{:storeId}")
    public ModelAndView createGoodsCounterrepost(@PathVariable("storeCode") String code){
        return null;        
    }
    
    @PostMapping("/addGoodsCountReport")
    public ModelAndView createAddGoodsCountrepost(@RequestBody GoodsPageRequest request){
        return null;        
    } 
    
    @PostMapping("/finReport")
    public ModelAndView createFinReport(){
        return null;        
    }
    
    
    
    
}
