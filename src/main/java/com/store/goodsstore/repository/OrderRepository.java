/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.repository;

import com.store.goodsstore.entities.Order;
import com.store.goodsstore.entities.Organization;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Lenovo
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {

    public List<Order> findByCustomerId(int id);  
    
    @Query("select o from Order o where o.date between :date1 and :date2 and o.org=:id")
    public List<Order> findByDate(@Param("date1") Date dateFrom, @Param("date2") Date dateTo,@Param("id") Organization org);
    
    public List<Order> findByGoodsId(int id);

}
