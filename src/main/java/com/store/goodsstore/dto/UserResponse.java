package com.store.goodsstore.dto;


import com.store.goodsstore.entities.Organization;
import com.store.goodsstore.persistent.State;
import lombok.Data;

/**
 *
 * @author YBolshakova
 */

@Data        
public class UserResponse {
       
    private int id;
    private String userEmail;     
    private Organization organization;
    private State state; 
   
     
   

}

