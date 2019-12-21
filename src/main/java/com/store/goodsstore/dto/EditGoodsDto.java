package com.store.goodsstore.dto;

import lombok.Data;

/**
 *
 * @author YBolshakova
 */
@Data
public class EditGoodsDto {
    private String name;    
    private String code;   
    private int groupId;    
    private double price;
   
}
