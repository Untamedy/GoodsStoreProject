/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositoryTests;

import com.store.goodsstore.GoodsstoreApplication;
import com.store.goodsstore.entities.GoodsGroup;
import com.store.goodsstore.entities.Store;
import com.store.goodsstore.repository.GroupRepository;
import com.store.goodsstore.repository.StoreRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Lenovo
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = GoodsstoreApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GroupRepositoryTest {
    
    @Autowired
    private GroupRepository repository;
    
    @Autowired
    private StoreRepository storeRepository;
    
    private Store store;
    
    @BeforeEach
    public void init(){
        store = storeRepository.findByCode("e8e4f1dd-5842-431a-a96d-706a27bc3953");
    }
    
    @Test
    public void saveGroup(){
        GoodsGroup group = new GoodsGroup();
        group.setName("g1");
        group.setStore(store);
        
        assertThat(null!=repository.save(group));
        assertThat(repository.findByName("g1").getStore().equals(store));
    }
    
    @Test
    public void fingGroup(){
        GoodsGroup group = new GoodsGroup();
        group.setName("g2");
        group.setStore(store);
        assertThat(null!=repository.findByName("g2"));
    }
    
    @Test
    public void findByStore(){
        GoodsGroup group = new GoodsGroup();
        group.setName("g2");
        group.setStore(store);
        assertThat(null!=repository.findByNameAndStoreId("g2", store.getId()));       
    }
    
}
