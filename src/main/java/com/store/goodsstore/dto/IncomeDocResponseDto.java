package com.store.goodsstore.dto;



import lombok.Data;

/**
 *
 * @author YBolshakova
 */
@Data
public class IncomeDocResponseDto {   
   private String orgCode;
    private String customer;
    private String goodsCode;
    private int quantity;
    private int groupId;
}
