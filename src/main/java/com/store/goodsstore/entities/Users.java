package com.store.goodsstore.entities;

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
import lombok.Data;

/**
 *
 * @author YBolshakova
 */
@Entity
@Data
public class Users {    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    @Column(nullable = false, unique = true)
    private String userEmail;
    @Column(nullable = false)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
    @ManyToOne
    @JoinColumn(name = "storeId")
    private Store store;
    @ManyToOne
    @JoinColumn(name = "orgId")
    private Organization orgatisation;
    

}
