package com.store.goodsstore.entities;

import java.util.List;
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
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn
    private List<Goods> goodsId;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private List<Store> storeId;
    private int quantity;
    
    

}
