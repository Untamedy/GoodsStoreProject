/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.dto;

import lombok.Data;

/**
 *
 * @author Lenovo
 */
@Data
public class GoodsOrderDto {
    
    private GoodsDto goodsDto;
    private int orderQuantity;
    
}
