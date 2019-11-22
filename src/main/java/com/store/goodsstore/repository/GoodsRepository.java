package com.store.goodsstore.repository;


import com.store.goodsstore.entities.Goods;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 *
 * @author YBolshakova
 */

public interface GoodsRepository extends JpaRepository<Goods, Integer>{

    public Goods findByCode(String code);
    
    public boolean existsByCode(String code); 
    
    public List<Goods> findByGroupId(int id);
    
}
