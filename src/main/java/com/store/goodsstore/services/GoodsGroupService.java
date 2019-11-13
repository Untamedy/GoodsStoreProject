package com.store.goodsstore.services;

import com.store.goodsstore.entities.GoodsGroup;
import com.store.goodsstore.repository.GroupRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author YBolshakova
 */
@Service
public class GoodsGroupService {
    
    @Autowired
    private GroupRepository repository;
    
    public List<GoodsGroup> getAllgroup(){
        return repository.findAll();        
    }
    
   

}
