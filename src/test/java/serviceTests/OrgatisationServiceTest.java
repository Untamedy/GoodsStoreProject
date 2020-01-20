/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceTests;

import com.store.goodsstore.dto.RegistrationRequest;
import com.store.goodsstore.entities.Organization;
import com.store.goodsstore.entities.Store;
import com.store.goodsstore.entities.Users;
import com.store.goodsstore.exceptions.RegistrationException;
import com.store.goodsstore.repository.OrganizationRepository;
import com.store.goodsstore.services.OrganizationService;
import com.store.goodsstore.services.StoreService;
import com.store.goodsstore.services.UserService;
import java.util.ArrayList;
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
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 *
 * @author Lenovo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrgatisationServiceTest {

    private static Organization organization;

    @Configuration
    static class TestConfig {

        @Bean
        public OrganizationService getService() {
            return new OrganizationService();
        }
    }

    @BeforeAll
    public static void init() {
        organization = new Organization();
        organization.setCode("11");
        organization.setCustomers(new ArrayList<>());
        organization.setEmail("org@mail.com");
        organization.setId(1);
        organization.setName("Org");
        organization.setStore(new Store());
        organization.setUsers(new Users());
    }

    @Autowired
    private OrganizationService service;

    @MockBean
    private UserService userService;

    @MockBean
    private StoreService storeService;

    @MockBean
    OrganizationRepository repository;

     
    @Test
    public void editOrganization() {
        RegistrationRequest request = new RegistrationRequest();
        request.setOrganizationEmail("org@mail.com");
        request.setOrganizationName("newName");
        request.setOrganizationEmail("newMail@mail.com");

        Organization editOrg = organization;
        editOrg.setEmail(request.getOrganizationEmail());
        editOrg.setName(request.getOrganizationName());

        Mockito.when(repository.findByEmail(request.getOrganizationEmail())).thenReturn(organization);
        Mockito.when(repository.save(organization)).thenReturn(editOrg);
        assertThat(service.editOrganization(request).getCode().equals(editOrg.getCode()));
        assertThat(service.editOrganization(request).getName().equals(editOrg.getName()));
    }    

    @Test
    public void createOrganization() {
        RegistrationRequest request = new RegistrationRequest();
        request.setOrganizationEmail("org@mail.com");
        request.setOrganizationName("newName");
        request.setOrganizationEmail("newMail@mail.com");
        Mockito.when(repository.findByEmail(request.getOrganizationEmail())).thenReturn(organization);
        Exception exception = assertThrows(RegistrationException.class, () -> {
            service.createOrganization(request);
        });
    }

}
