package com.store.goodsstore.repository;

import com.store.goodsstore.dto.StoreRequest;
import com.store.goodsstore.entities.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 *
 * @author YBolshakova
 */
public interface StoreRepositary extends JpaRepository<Store, Integer> {

    public boolean existsByName(String name);

    public Store findByName(String name);
    
    public boolean existsByStoreCode(String code);
    
    public Page<Store> findByOrgId(Integer id, Pageable page);
    
    public Store updateStore(String code);

}
