package com.store.goodsstore.repository;


import com.store.goodsstore.entities.Goods;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author YBolshakova
 */

public interface GoodsRepository extends JpaRepository<Goods, Integer>{

    public Goods findByCode(String code);
    
    public boolean existsByCode(String code);   
        
    @Query("update Goods set unit = ?1 where code = ?2")
    public Goods updateGoodsUnit(String unit, String code);        
    
    public List<Goods> findByGroupId(int groupId);
}
