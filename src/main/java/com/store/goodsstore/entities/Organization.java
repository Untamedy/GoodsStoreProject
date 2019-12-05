package com.store.goodsstore.entities;


import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import lombok.Data;

/**
 *
 * @author YBolshakova
 */
@Entity
@Data
@SequenceGenerator(name="my_seq", initialValue=1, allocationSize=1)
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    private Integer id;
    @Column(nullable = false,unique = true)
    private String name;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false,unique = true)
    private String code;
    
    @OneToMany(mappedBy = "org",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Store> store;
    
    @OneToOne(mappedBy="org",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Users users;
    
    @OneToMany(mappedBy="org",fetch= FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Customer> customers;
    
    
    
    
}
