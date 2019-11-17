package com.store.goodsstore.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
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
public class GoodsGroup {
    
    public GoodsGroup(String name){
        this.name = name;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    private Integer id;
    @Column(unique = true)
    private String name;    

}
