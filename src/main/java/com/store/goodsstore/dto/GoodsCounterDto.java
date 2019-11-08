package com.store.goodsstore.dto;

import lombok.Data;

/**
 *
 * @author YBolshakova
 */
@Data
public class GoodsCounterDto {
    
    private String goodsCode; 
    private String storeCode;
    private int quantity;
    

}
