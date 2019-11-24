/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.repository;

import com.store.goodsstore.entities.Order;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Lenovo
 */
public interface OrderRepository extends JpaRepository<Order, Integer>{
    
    public List<Order> findByCustomer(int id);
    
    public Order findByNum(String num);
    
    public Order findByNumAndOrdId(String num, int orgId);

     @Query("select * from Order where date between '?1' and '?2'")
    public List<Order> findByDate(Date dateFrom, Date dateTo);
    
}
