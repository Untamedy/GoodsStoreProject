/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.repository;

import com.store.goodsstore.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lenovo
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    
    public boolean existsByPhoneNum(String phoneNum);
    
    public Customer findByPhoneNumAndOrgCode(String phoneNum,String code);
    
    public Customer findByName(String name);
    
    
}
