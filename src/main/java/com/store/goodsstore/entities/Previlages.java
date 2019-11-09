package com.store.goodsstore.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import lombok.Data;

/**
 *
 * @author YBolshakova
 */
@Data
@Entity
@SequenceGenerator(name = "my_seq", sequenceName = "My_Seq",allocationSize = 1)
public class Previlages {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "my_seq")
    private Integer id;
    private String name;

}

 