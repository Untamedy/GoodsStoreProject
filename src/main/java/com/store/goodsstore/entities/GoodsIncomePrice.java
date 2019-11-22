/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Lenovo
 */
@Entity
@Data
@NoArgsConstructor
@SequenceGenerator(name="my_seq", initialValue=1, allocationSize=1)
public class GoodsIncomePrice {
    
      @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    private Integer id; 
    @OneToOne(mappedBy = "incomePrice")    
    private Goods goods;
    private double incomePrice;    

    public GoodsIncomePrice(double price) {
       this.incomePrice=price;
    }
    
}
