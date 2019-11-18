/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import lombok.Data;

/**
 *
 * @author Lenovo
 */
@Data
@Entity
@SequenceGenerator(name = "my_seq",sequenceName = "my_seq", allocationSize = 1)
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "my_seq")
    private int id;
    @Column(unique = true, nullable = false)
    private String phoneNum;
    @Column(nullable = false)   
    private String name;
}
