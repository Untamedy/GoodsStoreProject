package com.store.goodsstore.repository;

import com.store.goodsstore.entities.GoodsCounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author YBolshakova
 */
public interface GoodsCounterRepository extends JpaRepository<GoodsCounter, Integer> {
    
    @Query("select quantity from GoodsCounter where goodsCode = '?1' and storeCode = '?2'")
    public int getCount(String goodsId, String storeId);
    
    @Query("select sum(quantity) from GoodsCounter where goodsCode = '?1'")
    public int getGoodsSum(String goodsCode);
    
   // @Query("select * from GoodsCounter where store_id = '?1' and goods_id = '?2'")
    public GoodsCounter findByStoreIdAndGoodsId(int storeCode, int goodsCode);
    
    
    
    

}
