package com.store.goodsstore.controllers;

import com.store.goodsstore.dto.GoodsCounterDto;
import com.store.goodsstore.dto.GoodsDto;
import com.store.goodsstore.repository.GoodsCounterRepository;
import com.store.goodsstore.services.GoodsGroupService;
import com.store.goodsstore.services.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


/**
 *
 * @author YBolshakova
 */
@Controller("/goods")
public class GoodsController {
    
    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);
    
    @Autowired
   private GoodsService goodsService;
    @Autowired
    private GoodsGroupService groupService;
    @Autowired
    private GoodsCounterRepository goodsCounterRepository;
    
  
    
  @GetMapping("/list?groupId=id")
    public Page<GoodsDto> getGoodsByGroupId(@RequestParam("groupId") int id){        
        return goodsService.findByGroupId(id);        
    }
    
    @PostMapping("/saveGoods")
    public ModelAndView saveGoods(@RequestBody GoodsDto request){
        GoodsResponse response = goodsService.saveGoods(request);
        if(response!=null){
         return new ModelAndView("storePage", HttpStatus.OK);   
        }
       return new ModelAndView("addGoodsPage", HttpStatus.NOT_MODIFIED);
    }
    
    @PostMapping("/removeGoods")
    public ModelAndView removeGoods(@RequestBody GoodsDto request){
        if(goodsService.deleteGoods(request)){
         return new ModelAndView("storePage", HttpStatus.OK);   
        }
        return new ModelAndView("storePage",  HttpStatus.NOT_MODIFIED);
    }
    
    @PostMapping("/editGoods")
    public ModelAndView editGoods(@RequestBody GoodsDto request){
        GoodsDto response = goodsService.updateGoods(request);
        if(response!=null){
             return new ModelAndView("groupPage",HttpStatus.OK);       
        }
        return new ModelAndView("groupPage",HttpStatus.NOT_MODIFIED);        
    }
    
     @PostMapping("/updateCount")
    public ModelAndView addGoodsCount(@RequestBody GoodsCounterDto godsCounterDto){  
        
        
        return new ModelAndView();
        
    }
        
}
