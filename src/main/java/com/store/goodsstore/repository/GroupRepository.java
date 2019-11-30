package com.store.goodsstore.repository;


import com.store.goodsstore.entities.GoodsGroup;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author YBolshakova
 */
public interface GroupRepository extends JpaRepository<GoodsGroup,Integer>{    
    
 public GoodsGroup findByNameAndStoreId(String name,int id); 
 
 public boolean existsByNameAndStoreId(String name,int storeId);        
     
 public GoodsGroup findByName(String name);
 
 public List<GoodsGroup> findByStoreId(int id);
         

}
