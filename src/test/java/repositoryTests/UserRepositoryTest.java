/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositoryTests;

import com.store.entities.Organization;
import com.store.entities.Users;
import com.store.goodsstore.repository.OrganizationRepository;
import com.store.goodsstore.repository.RolesRepository;
import com.store.goodsstore.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.store.goodsstore.repository.StoreRepository;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.junit.jupiter.api.BeforeAll;
import static org.assertj.core.api.Assertions.*;
/**
 *
 * @author Lenovo
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest{
    
    @Autowired
    public UserRepository repository;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private OrganizationRepository orgrepository;
    @Autowired
    private StoreRepository storeRepositary;    
    @Autowired 
    private EntityManager entityManager;
    
    private final String path ="/src/main/resources/path.sql";
    private Organization organization;
    private Store store;
    private Set<Roles> roles;

    @BeforeAll
    public void init(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = "";
            while(null!=line){
                line = reader.readLine();
                Query query = entityManager.createNativeQuery(line);
                query.executeUpdate();
            }
        } catch (IOException ex) {
            Logger.getLogger(UserRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }   
    
    @BeforeAll
    public void createEntity(){
        organization =  orgrepository.findById(1).get();
        store = storeRepositary.findById("1").get();
        List<Role> r = rolesRepository.findAll();
        roles = new HashSet<>(r);        
    }
     
    
    @Test
    public void saveUsertest(){
        Users user = new Users();
        user.setOrgatisation(organization);
        user.setPassword("pass");
        user.setRoles(roles);
        user.setStore(store);
        user.setUserEmail("y.shemanska@gmail.com");
        user.setUserName("admin");      
        
        assertThat(null!=repository.save(user));
        
         
        
    }
    
    
    
}
