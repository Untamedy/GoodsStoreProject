/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositoryTests;

import com.store.goodsstore.GoodsstoreApplication;
import com.store.goodsstore.dto.UserRequest;
import com.store.goodsstore.entities.Organization;
import com.store.goodsstore.entities.Role;
import com.store.goodsstore.entities.Store;
import com.store.goodsstore.entities.Users;
import com.store.goodsstore.init.PrevilagesInit;
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
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.sql.DataSource;
import org.junit.jupiter.api.BeforeAll;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;

/**
 *
 * @author Lenovo
 */
//@ExtendWith(SpringExtension.class)
/*@SpringBootTest
@DataJpaTest
//@ContextConfiguration(classes = {GoodsstoreApplication.class})
public class UserRepositoryTest {

     @Autowired
    public static UserRepository repository;
    @Autowired
    private static RolesRepository rolesRepository;
    @Autowired
    private static OrganizationRepository orgrepository;
    @Autowired
    private static StoreRepository storeRepositary;    
    @Autowired 
    private static EntityManager entityManager;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private static EntityManager entityManager;
    @Autowired
    private static UserRepository userRepository;

    private static final String path = "/src/main/resources/path.sql";
    private static Organization organization;
    private static Store store;
    private static Set<Role> roles;
    private static Query query;

    @BeforeAll
    public static void init() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = "";
            while (null != line) {
                line = reader.readLine();
                entityManager.createQuery(line).executeUpdate();                
            }
        } catch (IOException ex) {
            Logger.getLogger(UserRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @BeforeEach
    public void createEntity() {
        organization = orgrepository.findById(1).get();
        store = storeRepositary.findById(1).get();
        List<Role> r = rolesRepository.findAll();
        roles = new HashSet<>(r);
    }

    @Test
    public void saveUsertest() {
        Users user = new Users();
        user.setOrgatisation(organization);
        user.setPassword("pass");
        user.setAuthorities(roles);
        user.setStore(store);
        user.setUserEmail("y.shemanska@gmail.com");
        user.setUsername("admin");

        assertThat(null != repository.save(user));
    }

}*/
