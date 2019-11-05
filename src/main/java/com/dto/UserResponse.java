package com.dto;


import com.entities.Organization;
import com.persistent.State;
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

