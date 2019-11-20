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
public class OrganizationDto {
    private int organizationId;
    private String organizationName;
    private String organizationEmail;
    private String storename;
    private String description;
    private String userName;
    private String userEmail;

}
