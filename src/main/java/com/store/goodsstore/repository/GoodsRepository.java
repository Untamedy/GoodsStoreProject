package com.store.goodsstore.repository;


import com.store.goodsstore.entities.Goods;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 *
 * @author YBolshakova
 */

public interface GoodsRepository extends PagingAndSortingRepository<Goods, Integer>{

    public Goods findByCode(String code);
    
    public boolean existsByCode(String code); 
    
    public Page<Goods> findByGroupId(int id,Pageable pageable);

    public List<Goods> findByGroupId(int groupId);
    
    
    
}
