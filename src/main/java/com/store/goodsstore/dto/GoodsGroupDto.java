/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.dto;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Lenovo
 */
@Data
@NoArgsConstructor
public class GoodsGroupDto {
    
    public GoodsGroupDto(String name){
        this.name = name;
    }
    
    private String name;
    private String storeCode;
    private List<GoodsDto> goods;
  
    
}
