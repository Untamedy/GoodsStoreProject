package com.store.goodsstore.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author YBolshakova
 */
@Entity
@Data
@NoArgsConstructor
@SequenceGenerator(name = "my_seq", initialValue = 1, allocationSize = 1)
public class GoodsCounter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    private Integer id;

    @OneToOne(mappedBy = "counter")  
    private Goods goods;   

    private int quantity;
    
    public GoodsCounter(int quantity){
        this.quantity = quantity;
        
    }

}
