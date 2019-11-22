/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.services;

import com.store.goodsstore.dto.OrderDto;
import com.store.goodsstore.entities.Order;
import com.store.goodsstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lenovo
 */
@Service
public class OrderService {
    
    @Autowired
    private OrderRepository repository;
    
    @Autowired
    private GoodsService goodsService;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private OrganizationService organizationService;
    
    public OrderDto saveOrder(OrderDto dto){
      return createDto(repository.save(createOrder(dto)));          
    }
    
    public Order createOrder(OrderDto dto){
        return null;
        
    }

    private OrderDto createDto(Order save) {
        return null;
        
    }
    
            
    
}
