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
import lombok.Data;

/**
 *
 * @author YBolshakova
 */
@Entity
@Data
@SequenceGenerator(name="my_seq", initialValue=1, allocationSize=1)
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    private Integer id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false, unique = true)
    private String code;
    private String unit;   
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private GoodsGroup goodsGroupId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    private Organization organization;   
    @Column(nullable = false)
    private boolean visible;    

}
