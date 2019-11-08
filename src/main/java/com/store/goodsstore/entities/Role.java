package com.store.goodsstore.entities;


import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Data;

/**
 *
 * @author YBolshakova
 */
@Data
@Entity

public class Role {   
   
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String roleName;
    //@ManyToMany(fetch = FetchType.EAGER)
    //private Set <Previlages> previlagesName;
    
    
     public Role(String name){
        this.roleName=name;
    }

}
