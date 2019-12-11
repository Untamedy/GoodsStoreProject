package com.store.goodsstore.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author YBolshakova
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude="org")
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
    
    //@OneToMany(mappedBy="store",fetch = FetchType.LAZY)    
    //private Set<GoodsGroup> groups = new HashSet<>();
    
   
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "org_id")    
    private Organization org;  

    public Store(String name, String code, Organization organization) {
       this.name = name;
       this.code=code;
       this.org = organization;
               
    }
    
   /* public void addGroup(GoodsGroup group){
        groups.add(group);
        group.setStore(this);
        
    }*/

   
    
    

}
