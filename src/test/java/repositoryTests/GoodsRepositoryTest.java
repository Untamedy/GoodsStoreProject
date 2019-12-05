/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositoryTests;

import com.store.goodsstore.GoodsstoreApplication;
import com.store.goodsstore.entities.Goods;
import com.store.goodsstore.entities.GoodsGroup;
import com.store.goodsstore.entities.Store;
import com.store.goodsstore.repository.GoodsRepository;
import com.store.goodsstore.repository.GroupRepository;
import com.store.goodsstore.repository.StoreRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
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
public class GoodsRepositoryTest {

    @Autowired
    private GoodsRepository repository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private GroupRepository groupRepository;

    private Store store;
    private GoodsGroup group;

    @BeforeEach
    public void init() {
        store = storeRepository.findByCode("e8e4f1dd-5842-431a-a96d-706a27bc3953");
        group = groupRepository.findByName("1");
    }

    @Test
    public void saveGoodsTest() {
        Goods goods = new Goods();
        goods.setCode("11");
        goods.setName("goods");
        goods.setUnit("kg");
        assertThat(null != repository.save(goods));
    }

    @Test
    public void findGoods() {
        Goods goods = new Goods();
        goods.setCode("12");
        goods.setName("goods1");
        goods.setUnit("kg");
        repository.save(goods);      
        assertThat(null != repository.findByCode("12"));      

    }

    @Test
    public void findByGroupId() {
        PageRequest pageable = PageRequest.of(2 - 1, 10);
        assertThat(null != repository.findByGroupId(1, pageable));
        assertThat(!repository.findByGroupId(1, pageable).getContent().isEmpty());
    }
    
    

}
