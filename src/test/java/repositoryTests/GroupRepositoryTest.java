/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositoryTests;

import com.store.goodsstore.GoodsstoreApplication;
import com.store.goodsstore.entities.GoodsGroup;
import com.store.goodsstore.entities.Organization;
import com.store.goodsstore.entities.Store;
import com.store.goodsstore.repository.GroupRepository;
import com.store.goodsstore.repository.OrganizationRepository;
import com.store.goodsstore.repository.StoreRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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

    private static Store store;
    private static Organization organization;

    @BeforeAll
    public static void init(@Autowired StoreRepository storeRepository,@Autowired OrganizationRepository orgRepository) {
       organization = new Organization("orgTest", "org@mail.com", "orgtestCode");
            store = new Store();
            store.setCode("22");
            store.setName("store2");                   
            organization.addStore(store);
            orgRepository.save(organization);
        
    }

    @Test
    public void saveGroup() {
        GoodsGroup group = new GoodsGroup("g2", store);
        assertThat(null != repository.save(group));
    }

    @Test
    public void fingGroup() {
        GoodsGroup group = new GoodsGroup("g2", store);
        repository.save(group);
        assertThat(null != repository.findByName("g2"));
    }

    @Test
    public void findByStore() {
        GoodsGroup group = new GoodsGroup("g2", store);
        repository.save(group);
        assertThat(group.equals(repository.findByNameAndStoreId("g2", store.getId())));
    }

    @AfterAll
    public static void cleanDB(@Autowired OrganizationRepository orgrepository, @Autowired StoreRepository storeRepository) {
        orgrepository.delete(organization);        

    }

}
