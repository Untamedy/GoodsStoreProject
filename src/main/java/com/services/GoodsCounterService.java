package com.services;

import com.dto.GoodsCounterDto;
import com.entities.GoodsCounter;
import com.repository.GoodsCounterRepository;
import com.repository.GoodsRepository;
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
    
    
    public GoodsCounter saveGoodsCount(GoodsCounterDto goodsCounterDto){
        if(goodsRepository.existsByCode(goodsCounterDto.getGoodsCode())){
         return repository.saveAndFlush(createGoodsCounter(goodsCounterDto));
        }else{
            
        }
        return null;
        
    }
    
    public GoodsCounter updateGoodsQuantity(GoodsCounterDto goodsCounterDto){
        if(goodsRepository.existsByCode(goodsCounterDto.getGoodsCode())){                       
          return repository.save(createGoodsCounter(goodsCounterDto));
        }
        return null;
        
    }
    
    public GoodsCounter increaseGoodsQuantity(GoodsCounterDto goodsCounterDto){           
        GoodsCounter counter = repository.findByStoreCode(goodsCounterDto.getStoreCode(), goodsCounterDto.getGoodsCode());
        if(null!=counter){
            int quantity = counter.getQuantity();
            quantity+=goodsCounterDto.getQuantity();
            counter.setQuantity(quantity);
            return repository.save(counter);
        }           
         return counter;
        }
        
             
    public GoodsCounter decreaseGoodsQuantity(GoodsCounterDto goodsCounterDto){
         GoodsCounter counter = repository.findByStoreCode(goodsCounterDto.getStoreCode(), goodsCounterDto.getGoodsCode());
        if(null!=counter){
            int quantity = counter.getQuantity();
            if(quantity>=goodsCounterDto.getQuantity()){
               quantity-=goodsCounterDto.getQuantity();
            counter.setQuantity(quantity);
            return repository.save(counter); 
            }            
        }           
         return counter;
    }
    
    public GoodsCounter createGoodsCounter(GoodsCounterDto goodsCounterDto){            
        return repository.findByStoreCode(goodsCounterDto.getStoreCode(), goodsCounterDto.getGoodsCode());
        
    }
    

}
