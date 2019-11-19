package com.store.goodsstore.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author YBolshakova
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name="my_seq", initialValue=1, allocationSize=1)
public class Store implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    private Integer id;
    
    @Column(unique = true)
    private String name;       
    
    @Column(length = 255)
    private String description;   
    
    @Column(unique = true, nullable = false)
    public String code;
    
    @OneToMany(mappedBy="store", fetch = FetchType.LAZY)    
    private List<GoodsGroup> groups;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orgId")    
    private Organization org;  
    
    

}
