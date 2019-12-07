/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositoryTests;

import com.store.goodsstore.GoodsstoreApplication;
import com.store.goodsstore.entities.Organization;
import com.store.goodsstore.entities.Role;
import com.store.goodsstore.entities.Users;
import com.store.goodsstore.repository.OrganizationRepository;
import com.store.goodsstore.repository.RolesRepository;
import com.store.goodsstore.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
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
public class UserRepositoryTest {

    @Autowired
    public UserRepository repository;

    private static Organization organization;
    private static Set<Role> roles;
    private static Users user;

    @BeforeAll
    public static void createEntity(@Autowired OrganizationRepository orgrepository, @Autowired RolesRepository rolesRepository) {
        organization = new Organization("org", "test2Org@mail.com", "2222");
        orgrepository.save(organization);
        List<Role> r = rolesRepository.findAll();
        roles = new HashSet<>(r);
    }

    @Test
    public void saveUsertest() {
        user = new Users();
        user.setOrg(organization);
        user.setPassword("pass");
        user.setRoles(roles);
        user.setEmail("Test@gmail.com");
        user.setName("admin");
        assertThat(null != repository.save(user));
    }

    @Test
    public void findUserByEmail() {
        user = new Users();
        user.setOrg(organization);
        user.setPassword("pass");
        user.setRoles(roles);
        user.setEmail("Test@gmail.com");
        user.setName("admin");
        repository.save(user);
        assertThat(repository.findByEmail("Test@gmail.com"));
    }

    @Test
    public void findByName() {
        user = new Users();
        user.setOrg(organization);
        user.setPassword("pass");
        user.setRoles(roles);
        user.setEmail("Test@gmail.com");
        user.setName("admin");
        repository.save(user);
        assertThat(null != repository.findByName("admin"));
    }

    @AfterAll
    public static void cleanDB(@Autowired OrganizationRepository orgrepository, @Autowired RolesRepository rolesRepository) {        
        orgrepository.delete(organization);
    }

}
