package com.store.goodsstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author YBolshakova
 */
@Data
@AllArgsConstructor
public class RegistrationResponse {
    
    private UserDto userDto;
    private OrganizationDto organisationDto; 
    private StoreDto storeDto;

}
