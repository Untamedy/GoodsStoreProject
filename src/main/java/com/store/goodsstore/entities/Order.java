/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.entities;

import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import lombok.Data;

/**
 *
 * @author Lenovo
 */
@Entity
@Data
@SequenceGenerator(name = "my_seq", initialValue = 1, allocationSize = 1)
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "my_seq")
    private int id;
   
    private String num;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="customer_id")
    private Customer customer;
    
    @OneToMany(fetch=FetchType.EAGER)
    @JoinTable(name="order_goods")
    private Set<Goods> goods;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="org_id")
    private Organization org;
    private int sum;
    
}
