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
 * @author YBolshakova
 */
@Entity
@Data
@SequenceGenerator(name="my_seq", initialValue=1, allocationSize=1)
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String organizationEmail;
    @Column(nullable = false)
    private String identificationCode;
}