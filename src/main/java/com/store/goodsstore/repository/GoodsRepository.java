package com.store.goodsstore.repository;


import com.store.goodsstore.entities.Goods;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author YBolshakova
 */

public interface GoodsRepository extends JpaRepository<Goods, Integer>{

    public Goods findByCode(String code);
    
    public boolean existsByCode(String code);
    
    @Query("update Goods set name = ?1 where code = ?2")
    public Goods updateGoodsName(String name, String code);   
        
    @Query("update Goods set unit = ?1 where code = ?2")
    public Goods updateGoodsQuantity(String unit, String code);
    
    public Page<Goods> findByStoreId(Integer storeId, Pageable page);
    
    @Query("select count(id) from Goods where storeId = ?1")
    public int goodsCount(Integer storeId);
}
