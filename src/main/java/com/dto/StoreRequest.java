package com.dto;

import com.entities.Organization;
import lombok.Data;

/**
 *
 * @author YBolshakova
 */
@Data
public class StoreRequest {
    
    private String name;
    private String code;
    private String description;
    private Organization organization;

}
