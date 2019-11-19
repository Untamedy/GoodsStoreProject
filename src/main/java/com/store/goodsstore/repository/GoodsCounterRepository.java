package com.store.goodsstore.repository;

import com.store.goodsstore.entities.GoodsCounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author YBolshakova
 */
public interface GoodsCounterRepository extends JpaRepository<GoodsCounter, Integer> {    
        
    @Query("select * from GoodsCounter where store_id = '?1' and goods_id = '?2'")
    public GoodsCounter findByStoreIdAndGoodsId(int storeCode, int goodsCode);  
    
    public int countByGoodsCode(String code);
}
