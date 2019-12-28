package com.store.goodsstore.dto;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author YBolshakova
 */
@Data
public class IncomeDocDto {
    private Date date;
    private String num;
    private String orgName;
    private String customerName;
     private String customerPhone;
    private String goodsName;    
    private int quantity;
    private double sum;

}
