/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceTests;

import com.store.goodsstore.dto.GoodsDto;
import com.store.goodsstore.entities.Goods;
import com.store.goodsstore.entities.GoodsCounter;
import com.store.goodsstore.entities.GoodsGroup;
import com.store.goodsstore.entities.Order;
import com.store.goodsstore.repository.GoodsRepository;
import com.store.goodsstore.services.GoodsCounterService;
import com.store.goodsstore.services.GoodsGroupService;
import com.store.goodsstore.services.GoodsService;
import com.store.goodsstore.services.IncomDocService;
import com.store.goodsstore.services.OrderService;
import com.store.goodsstore.services.StoreService;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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

    private static Goods goods;
    private static GoodsDto dto;
    private static GoodsGroup group;

    @Configuration
    static class TestConfig {

        @Bean
        public GoodsService getService() {
            return new GoodsService();
        }
    }

    @BeforeAll
    public static void init() {
        
        
        goods = new Goods();
        goods.setId(1);
        goods.setCode("11");        
        goods.setCounter(new GoodsCounter(1));
        
        dto = new GoodsDto();
        dto.setCode(goods.getCode());
        dto.setName(goods.getName());
        

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
    public void saveGoods() {
        String code = "11";
        Mockito.when(repository.existsByCode(code)).thenReturn(true);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.saveGoods(dto);
        });
    }

    
    @Test
    public void deleteGoodsWithCountNotNull() {
        String code = "11";
        Mockito.when(repository.findByCode(code)).thenReturn(goods);
        
        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.deleteGoods(goods.getCode());
        });

    }
    
    @Test
    public void deleteGoodsIfItUsed(){
        String code = "11";
        List<Order> orders = new ArrayList<>();
        orders.add(new Order());
        goods.setCounter(new GoodsCounter(0));
        Mockito.when(repository.findByCode(code)).thenReturn(goods);
        Mockito.when(orderService.ordersConteinGoods(goods.getId())).thenReturn(orders);    
        
        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.deleteGoods(goods.getCode());
        });
        
    }

}
