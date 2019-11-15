package com.store.goodsstore.repository;


import com.store.goodsstore.entities.Store;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 *
 * @author YBolshakova
 */
public interface StoreRepository extends JpaRepository<Store, Integer> {

    public boolean existsByName(String name);

    public Store findByName(String name);   
        
    public List<Store> findByOrgId(Integer id);
    
  

}
