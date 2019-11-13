package com.store.goodsstore.controllers;

import com.store.goodsstore.dto.GoodsDto;
import com.store.goodsstore.dto.GoodsPageRequest;
import com.store.goodsstore.dto.GoodsResponse;
import com.store.goodsstore.entities.GoodsGroup;
import com.store.goodsstore.repository.GroupRepository;
import com.store.goodsstore.services.GoodsGroupService;
import com.store.goodsstore.services.GoodsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 *
 * @author YBolshakova
 */
@Controller("/goods")
public class GoodsController {
    
    @Autowired
   private GoodsService goodsService;
    @Autowired
    private GoodsGroupService groupService;
    
    @GetMapping("/listGroups")
    public ModelAndView getAllGroup(){
        List<GoodsGroup> allGroup = groupService.getAllgroup();
        return new ModelAndView("storePage", "listOfGroups", allGroup);        
    }
    
  @PostMapping("/list")
    public Page<GoodsResponse> getGoodsByStore(@RequestBody GoodsPageRequest request ){        
        return goodsService.findByStoreIdAndGroupId(request.getStoreId(), request);        
    }
    
    @PostMapping("/saveGoods")
    public ModelAndView saveGoods(@RequestBody GoodsDto request){
        GoodsResponse response = goodsService.saveGoods(request);
       return new ModelAndView("storePage", HttpStatus.OK);
    }
    
    @PostMapping("/remove")
    public ModelAndView removeGoods(@RequestBody GoodsDto request){
        if(goodsService.deleteGoods(request)){
         return new ModelAndView("storePage", HttpStatus.OK);   
        }
        return new ModelAndView("storePage",  HttpStatus.NOT_MODIFIED);
    }
    
    @PostMapping("/edit")
    public ModelAndView editGoods(@RequestBody GoodsDto request){
        GoodsResponse response = goodsService.updateGoodsName(request);
        if(response!=null){
             return new ModelAndView("groupPage",HttpStatus.OK);       
        }
        return new ModelAndView("groupPage",HttpStatus.NOT_MODIFIED);        
    }
    
  @PostMapping("/sale")
  public ModelAndView saleGoods(@ResponseBody List<GoodsResponse> goods){
        return null;
      
  }
          

    
    
}
