/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositoryTests;

import com.store.goodsstore.GoodsstoreApplication;
import com.store.goodsstore.entities.Organization;
import com.store.goodsstore.entities.Store;
import com.store.goodsstore.repository.OrganizationRepository;
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
public class StoreRepositoryTest {
    
    @Autowired
    private StoreRepository repository;
    
    @Autowired
    private OrganizationRepository orgRepository;
            
    
    private Organization org;
    
    @BeforeEach
    public void getOrg(){
        org = orgRepository.findByEmail("y.shemanska@gmail.com");
    }
    
    
    @Test
    public void saveStore(){
        Store store = new Store();
        store.setCode("11");
        store.setName("store");
        store.setOrg(org);
        assertThat(null!=repository.save(store));
        assertThat(repository.findByCode("11").getOrg().equals(org));       
        
    }
    
    
}
