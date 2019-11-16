package com.store.goodsstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author YBolshakova
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResponse {
    
    private UserDto userDto;
    private OrganizationDto organisationDto; 
    private StoreDto storeDto;

}
