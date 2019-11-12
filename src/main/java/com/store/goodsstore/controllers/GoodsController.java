package com.store.goodsstore.controllers;

import com.store.goodsstore.dto.GoodsDto;
import com.store.goodsstore.dto.GoodsResponse;
import com.store.goodsstore.services.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author YBolshakova
 */
@Controller("/goods")
public class GoodsController {
    
    @Autowired
    GoodsService goodsService;
    
 /*   @RequestMapping(method = RequestMethod.POST, value = "/list")
    public Page<GoodsResponse> getGoodsByStore(@RequestBody GoodsPageRequest request ){        
        return goodsService.findByStoreId(request.getStoreId(), request);        
    }*/
    
    @RequestMapping(method = RequestMethod.POST, value = "/saveGoods")
    public ResponseEntity<GoodsResponse> saveGoods(@RequestBody GoodsDto request){
        GoodsResponse response = goodsService.saveGoods(request);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/remove")
    public ResponseEntity<String> removeGoods(@RequestBody GoodsDto request){
        if(goodsService.deleteGoods(request)){
         return new ResponseEntity<>("Goods " + request.getName() + " remove successful", HttpStatus.OK);   
        }
        return new ResponseEntity<>("Goods " + request.getName() + " didn't remove", HttpStatus.NOT_MODIFIED);
    }
    
  /*  @RequestMapping(method = RequestMethod.POST, value = "/goodsByStore{:id}")
    public Page<GoodsResponse> getGoodsByStore(@PathVariable int id, @RequestBody GoodsPageRequest request){
       return goodsService.findByStoreId(id, request);
        
    }*/

}
