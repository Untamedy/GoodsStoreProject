package com.store.goodsstore.dto;

import lombok.Data;

/**
 *
 * @author YBolshakova
 */
@Data
public class RegistrationRequest {
    private String organizationName;
    private String organizationEmail;
    private String userName;
    private String userEmail;
    private String userPass; 
    private String storeCode;
    private String storeName;
    private String storeDiscription;
}
