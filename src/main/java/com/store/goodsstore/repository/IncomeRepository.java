package com.store.goodsstore.repository;

import com.store.goodsstore.entities.IncomingDoc;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author YBolshakova
 */
@Repository
public interface IncomeRepository extends JpaRepository<IncomingDoc, Integer>{
    

    
    public List<IncomingDoc> findAllByDateBetween(Date dateFrom, Date dateTo);
    
    public IncomingDoc findByNum(String num);

    public List<IncomingDoc> findByCustomer(int id);

 

}
