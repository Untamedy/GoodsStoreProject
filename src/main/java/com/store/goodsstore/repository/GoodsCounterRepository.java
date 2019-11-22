package com.store.goodsstore.repository;

import com.store.goodsstore.entities.GoodsCounter;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author YBolshakova
 */
public interface GoodsCounterRepository extends JpaRepository<GoodsCounter, Integer> { 
    
    public int countQantityByGoodsCode(String code);   

    public GoodsCounter findByGoodsCode(String code);
}
