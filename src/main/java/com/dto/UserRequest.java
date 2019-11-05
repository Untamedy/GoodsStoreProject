package com.dto;

import com.entities.Organization;

import lombok.Data;

/**
 *
 * @author YBolshakova
 */
@Data
public class UserRequest {

    private String userName;
    private String userEmail;
    private String password;
    private Organization organization;

}
