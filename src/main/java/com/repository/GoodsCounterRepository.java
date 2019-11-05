package com.repository;

import com.entities.GoodsCounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author YBolshakova
 */
public interface GoodsCounterRepository extends JpaRepository<GoodsCounter, Integer> {
    
    @Query("select quantity from GoodsCounter where goodsCode = '?1' and storeCode = '?2'")
    public int getCount(String goodsId, String storeId);
    
    @Query("select sum quentity from GoodsCounter where goodsCode = '?1'")
    public int getGoodsSum(String goodsCode);
    
    @Query("select * from GoodsCounter where storeCode = '?1' and goodsCode = '?2'")
    public GoodsCounter findByStoreCode(String storeCode, String goodsCode);
    
    
    
    

}
