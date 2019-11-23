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
    
    public IncomingDoc findByNum(int num);
    
    @Query("select * from IncomeDoc where date between '?1' and '?2'")
    public List<IncomingDoc> findByDate(Date dateFrom, Date dateTo);

 

}
