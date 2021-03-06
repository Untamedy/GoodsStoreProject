package com.store.goodsstore.dto;

import lombok.Data;

/**
 *
 * @author YBolshakova
 */
@Data
public class GoodsDto {
    
    private String name;
    private String code;    
    private String unit;
    private int groupId;
    private int quantity;
    private double price;  
    private double incomePrice;    
}
