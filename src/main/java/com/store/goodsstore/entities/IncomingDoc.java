/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.entities;

import java.util.Date;
import java.util.List;
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
public class IncomingDoc {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private int num;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date incomDate;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "org_id")
    private Organization org;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="customer_id")
    private Customer customer;
    
    @OneToMany(fetch=FetchType.EAGER)
    @JoinTable(name="order_goods")
    private List<Goods> goods;
    
    private double incomSum;
}
