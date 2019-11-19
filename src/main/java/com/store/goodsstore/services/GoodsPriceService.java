/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.services;

import com.store.goodsstore.dto.GoodsDto;
import com.store.goodsstore.entities.GoodsPrice;
import com.store.goodsstore.repository.GoodsPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author Lenovo
 */
@Service
public class GoodsPriceService {
    
    @Autowired
    private GoodsPriceRepository repository;
    
    
    public GoodsPrice createGoodsPrice(GoodsDto goodsDto){
       return new GoodsPrice(goodsDto.getPrice());        
    }
    
    
}
