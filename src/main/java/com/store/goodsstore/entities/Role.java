package com.store.goodsstore.entities;



import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author YBolshakova
 */
@Data
@NoArgsConstructor
@Entity
@SequenceGenerator(name="my_seq", initialValue=1, allocationSize=1)
public class Role {   
   
   
    @Id   
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    private int id;
    @Column(unique = true)
    private String roleName;
    @OneToMany(fetch = FetchType.LAZY)
    private Set<Previlages> previlagesName;
    
    
     public Role(String name){
        this.roleName=name;
    }

}
