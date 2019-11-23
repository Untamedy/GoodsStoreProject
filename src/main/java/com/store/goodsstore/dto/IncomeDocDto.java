package com.store.goodsstore.dto;


import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 *
 * @author YBolshakova
 */
@Data
public class IncomeDocDto {

    private int num;
    private Date incomeDate;
    private String orgName;
    private String customer;
    private List<GoodsDto> goods;
    private double incomSum;
}
