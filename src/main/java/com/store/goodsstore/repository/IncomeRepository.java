package com.store.goodsstore.repository;

import com.store.goodsstore.entities.IncomingDoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author YBolshakova
 */
@Repository
public interface IncomeRepository extends JpaRepository<IncomingDoc, Integer>{
    
    public IncomingDoc findByNum(int num);

 

}
