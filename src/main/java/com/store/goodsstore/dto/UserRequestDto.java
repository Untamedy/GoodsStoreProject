/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.dto;

import com.store.goodsstore.entities.Organization;
import com.store.goodsstore.persistent.State;
import lombok.Data;

/**
 *
 * @author Lenovo
 */
@Data
public class UserRequestDto {    
    private String username;
    private String userEmail;
    private String password;
    private Organization organization;
    private State state;

}
