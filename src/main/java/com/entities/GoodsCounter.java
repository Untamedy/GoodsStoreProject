package com.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import lombok.Data;


/**
 *
 * @author YBolshakova
 */
@Entity
@Data
public class GoodsCounter {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id; 
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn
    private Goods goodsId;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn
    private Store storeId;
    private int quantity;
    
    

}
