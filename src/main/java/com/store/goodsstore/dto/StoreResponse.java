package com.store.goodsstore.dto;

import com.store.goodsstore.entities.Organization;
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
public class StoreResponse {
    private String name;
    private String code;
    private String description;
    private Organization organization;
      

}