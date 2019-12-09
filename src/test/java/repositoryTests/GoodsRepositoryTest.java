/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositoryTests;

import com.store.goodsstore.GoodsstoreApplication;
import com.store.goodsstore.entities.Goods;
import com.store.goodsstore.entities.GoodsGroup;
import com.store.goodsstore.entities.Organization;
import com.store.goodsstore.entities.Store;
import com.store.goodsstore.repository.GoodsRepository;
import com.store.goodsstore.repository.GroupRepository;
import com.store.goodsstore.repository.OrganizationRepository;
import com.store.goodsstore.repository.StoreRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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

    private static Store store;
    private static GoodsGroup group;
    private static Organization organization;
    private static Goods goods;
    private PageRequest pageable;

    @BeforeAll
    public static void init(@Autowired OrganizationRepository orgRepozitory, @Autowired StoreRepository storeRepository, @Autowired GroupRepository groupRepository) {

        organization = orgRepozitory.findByEmail("y.shemanska@gmail.com");
        store = new Store("store2", "24", organization); 
        storeRepository.save(store);        
        group = groupRepository.save(new GoodsGroup("g1", store));
       
    }

    @Test
    public void saveGoodsTest() {
        goods = new Goods();
        goods.setCode("11");
        goods.setName("goods");
        goods.setUnit("kg");
        goods.setGroup(group);
        assertThat(null != repository.save(goods));
    }

    @Ignore
    @Test
    public void findGoods() {
        goods = new Goods();
        goods.setCode("11");
        goods.setName("goods");
        goods.setUnit("kg");
        goods.setGroup(group);
        repository.save(goods);
        assertThat(null != repository.findByCode("11"));
    }

    @Ignore
    @Test
    public void findByGroupId() {
        goods = new Goods();
        goods.setCode("11");
        goods.setName("goods");
        goods.setUnit("kg");
        goods.setGroup(group);
        repository.save(goods);
        pageable = PageRequest.of(2 - 1, 10);
        assertThat(null != repository.findByGroupId(group.getId(), pageable));
        assertThat(!repository.findByGroupId(group.getId(), pageable).getContent().isEmpty());
    }

    @AfterAll
    public static void cleanDb(@Autowired OrganizationRepository orgRepository, @Autowired StoreRepository storeRepository, @Autowired GroupRepository groupRepository) {
        groupRepository.delete(group);
        storeRepository.delete(store);        
    }

}
