package com.store.goodsstore.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author YBolshakova
 */
@Entity
@Data
@NoArgsConstructor
@SequenceGenerator(name="my_seq", initialValue=1, allocationSize=1)
@Table(name="group")
public class GoodsGroup {
    
    public GoodsGroup(String name){
        this.name = name;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    private Integer id;   
    
    private String name;  
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="store_id")
    private Store store;
    
    @OneToMany(mappedBy="group",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Goods> goods;

    public GoodsGroup(String name, Store store) {
        this.name=name;
        this.store=store;
    }

}
