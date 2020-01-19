/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceTests;

import com.store.goodsstore.entities.Goods;
import com.store.goodsstore.entities.GoodsGroup;
import com.store.goodsstore.repository.GoodsRepository;
import com.store.goodsstore.services.GoodsCounterService;
import com.store.goodsstore.services.GoodsGroupService;
import com.store.goodsstore.services.GoodsService;
import com.store.goodsstore.services.IncomDocService;
import com.store.goodsstore.services.OrderService;
import com.store.goodsstore.services.StoreService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Lenovo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsServiceTest {
    
    private Goods goods;
    private GoodsGroup group;
    
    @Configuration
    static class TestConfig{
        
        @Bean
        private GoodsService getService(){
            return new GoodsService();
        }    
}
    @Autowired
    private GoodsService service;
    
    @MockBean   
    private GoodsRepository repository;
    @MockBean
    private GoodsCounterService goodsCounterSecvice;
    @MockBean
    private GoodsGroupService groupService;
    @MockBean
    private StoreService storeService;
    @MockBean
    private OrderService orderService;    
    @MockBean
    private IncomDocService incomeService;
    
    @Test
    public void saveGoods(){
        
    }
    @Test
    public void updateGoods(){
        
    }
    @Test
    public void deleteGoods(){
        
    }
    @Test
    public void goodsCount(){
        
    }
    @Test
    public void fingByCode(){
        
    }
    @Test
    public void existByCode(){
        
    }
      @Test
    public void createGoodsPrice(){
        
    }
    @Test
    public void createIncomePrice(){
        
    }
    @Test
    public void getByGroupId(){
        
    }
    @Test
    public void createGoodsResponse(){
        
    }
    @Test
    public void getPaginatedGoods(){
        
    }
    
    
    
    
}
