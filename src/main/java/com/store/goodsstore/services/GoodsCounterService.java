package com.store.goodsstore.services;

import com.store.goodsstore.dto.GoodsCounterDto;
import com.store.goodsstore.dto.GoodsDto;
import com.store.goodsstore.entities.GoodsCounter;
import com.store.goodsstore.repository.GoodsCounterRepository;
import com.store.goodsstore.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author YBolshakova
 */
@Service
public class GoodsCounterService {
    
    @Autowired
    GoodsCounterRepository repository;
    @Autowired
    GoodsService goodsService;    
    @Autowired
    GoodsRepository goodsRepository;
    
    
  
    
   
    //необходимо дописать логику 
    public GoodsCounter increaseGoodsQuantity(GoodsCounterDto goodsCounterDto){           
        return null;
        
        }
        
             
    public GoodsCounter decreaseGoodsQuantity(GoodsCounterDto goodsCounterDto){
         return null;
    }
    
    public GoodsCounter createGoodsCounter(GoodsDto goods){  
        return new GoodsCounter(goods.getQuantity());
        
    }
    

}
