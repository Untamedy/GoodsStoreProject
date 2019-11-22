/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.repository;

import com.store.goodsstore.entities.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Lenovo
 */
public interface OrderRepository extends JpaRepository<Order, Integer>{
    
    public List<Order> findByCustomer(int id);
    
    
    
}
