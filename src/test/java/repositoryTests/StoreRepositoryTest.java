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
import com.store.goodsstore.repository.RolesRepository;
import com.store.goodsstore.repository.StoreRepository;
import static org.assertj.core.api.Assertions.assertThat;
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
public class StoreRepositoryTest {
    
    @Autowired
    private StoreRepository repository;
    
    @Autowired
    private static OrganizationRepository orgRepository;
            
    
    private static Organization organization;
    
   @BeforeAll
    public static  void createEntity(@Autowired OrganizationRepository orgrepository,@Autowired RolesRepository rolesRepository) {  
       organization = orgrepository.findByEmail("test2Org@mail.com"); 
        if(organization==null){
             organization = new Organization("org","test2Org@mail.com","2222");
             orgrepository.save(organization);
        }               
    }
    
    
    @Test
    public void saveStore(){        
        Store store = new Store("store","11",organization);        
        assertThat(null!=repository.save(store));
    }
    
    @Test
    public void findByCode(){       
        Store store = new Store("store4","24",organization);  
        repository.save(store);        
        assertThat(repository.findByCode("22").getOrg().equals(organization)); 
    }
    
    @Test
    public void findByName(){
         Store store = new Store("store3","23",organization);  
        repository.save(store);        
        assertThat(repository.findByName("store3").getOrg().equals(organization)); 
    }
    
    
}
