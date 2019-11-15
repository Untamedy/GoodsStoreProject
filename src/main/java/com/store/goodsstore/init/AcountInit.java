package com.store.goodsstore.init;

import com.store.goodsstore.dto.RegistrationRequest;
import com.store.goodsstore.repository.UserRepository;
import com.store.goodsstore.services.RegistrationService;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author YBolshakova
 */
@Component
public class AcountInit {
    private static final Logger logger = LoggerFactory.getLogger(AcountInit.class);

    @Autowired
    private RegistrationService service;

    @PostConstruct
    public void saveAcount() {
        service.register(createRequest());
    }

    public RegistrationRequest createRequest() {
        RegistrationRequest request = new RegistrationRequest();
        request.setOrganizationName("TestStore");
        request.setOrganizationEmail("y.shemanska@gmail.com");
        request.setStoreCode("test01");        
        request.setStoreName("FirstStore");
        request.setUserEmail("y.shemanska@gmail.com");
        request.setUserName("Me");
        request.setUserPass("123");
        return request;
    }

}
