package com.store.goodsstore.dto;

import lombok.Data;


/**
 *
 * @author YBolshakova
 */
@Data
public class GoodsPageRequest {
    
    private Integer storeId;
    private int page;
    private int size;   
   

}
