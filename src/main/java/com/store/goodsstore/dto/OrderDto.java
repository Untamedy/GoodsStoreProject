/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.dto;

import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Lenovo
 */
@Data
public class OrderDto {

    private String orderNum;
    private Date orderDate;
    private String orgName;
    private String orgCode;
    private String customerName;
    private String customerPhone;
    private List<GoodsDto> goods;
    private double orderSum;
    

}
