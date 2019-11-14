package com.store.goodsstore.entities;

import com.store.goodsstore.persistent.State;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author YBolshakova
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name="my_seq", initialValue=1, allocationSize=1)
public class Users {    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    private Integer id;
    private String username;
    @Column(nullable = false, unique = true)
    private String userEmail;
    @Column(nullable = false)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> authorities;    
    @ManyToOne
    @JoinColumn(name = "orgId")
    private Organization orgatisation; 
    private State state;
   

    

   

   

   

   

   

   

    

}
