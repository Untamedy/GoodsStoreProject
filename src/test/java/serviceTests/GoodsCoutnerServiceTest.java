/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceTests;

import com.store.goodsstore.dto.GoodsDto;
import com.store.goodsstore.dto.IncomeDocResponseDto;
import com.store.goodsstore.entities.Goods;
import com.store.goodsstore.repository.GoodsCounterRepository;
import com.store.goodsstore.repository.GoodsRepository;
import com.store.goodsstore.services.GoodsCounterService;
import com.store.goodsstore.services.GoodsService;
import java.util.ArrayList;
import java.util.List;
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
public class GoodsCoutnerServiceTest {

    private static IncomeDocResponseDto incomDto;

    private static List<GoodsDto> goodsDto = new ArrayList<>();

    @Configuration
    static class Testconfig {

        @Bean
        public GoodsCounterService getCoutner() {
            return new GoodsCounterService();
        }

    }
    @Autowired
    private GoodsCounterService service;
    @MockBean
    GoodsCounterRepository repository;
    @MockBean
    GoodsService goodsService;
    @MockBean
    GoodsRepository goodsRepository;

    @BeforeAll
    public static void init() {
        GoodsDto goodsDtos = new GoodsDto();
        goodsDtos.setCode("11");
        goodsDtos.setGroupId(1);
        goodsDtos.setIncomePrice(1);
        goodsDtos.setName("Goods");
        goodsDtos.setPrice(1);
        goodsDtos.setQuantity(1);
        goodsDtos.setUnit("kg");

        goodsDto.add(goodsDtos);

        incomDto = new IncomeDocResponseDto();
        incomDto.setCustomer("Customer");
        incomDto.setGoodsCode("11");
        incomDto.setGroupId(1);
        incomDto.setIncomePrice(1);
        incomDto.setOrgCode("12");
        incomDto.setQuantity(1);

    }

    @Test
    public void increaseGoodsQuantityTest() {
        String code = "11";
        Goods goods = new Goods();
        goods.setCode("11");
        Mockito.when(goodsService.goodsCount(code)).thenReturn(1);
        Mockito.when(goodsService.fingByCode(code)).thenReturn(goods);
        
                
                }

    @Test
    public void decreaseGoodsQuantityTest() {

    }

}
