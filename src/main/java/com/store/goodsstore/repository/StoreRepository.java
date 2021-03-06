package com.store.goodsstore.repository;


import com.store.goodsstore.entities.Store;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 *
 * @author YBolshakova
 */
public interface StoreRepository extends JpaRepository<Store, Integer> {

    public boolean existsByCode(String code);

    public Store findByCode(String code); 
    
    public Store findByName(String name);
        
    public Store findByOrg(Integer id);
    
  

}
