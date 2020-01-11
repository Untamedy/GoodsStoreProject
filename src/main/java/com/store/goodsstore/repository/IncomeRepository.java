package com.store.goodsstore.repository;

import com.store.goodsstore.entities.IncomingDoc;
import com.store.goodsstore.entities.Organization;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author YBolshakova
 */
@Repository
public interface IncomeRepository extends JpaRepository<IncomingDoc, Integer>{
    
     @Query("select d from IncomingDoc d where d.date between :date1 and :date2 and d.org=:id")
    public List<IncomingDoc> findByDate(@Param("date1") Date dateFrom, @Param("date2") Date dateTo,@Param("id") Organization org);    

    public List<IncomingDoc> findByCustomerId(int id);
    
    public List<IncomingDoc> findByGoodsId(int id);

 

}
