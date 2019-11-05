package com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author YBolshakova
 */
@Data
@AllArgsConstructor
public class RegistrationResponse {
    
    private UserResponse userRespons;
    private OrganizationResponse organisationResponse; 
    private StoreResponse storeResponse;

}
