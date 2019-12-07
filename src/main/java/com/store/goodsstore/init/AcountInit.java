package com.store.goodsstore.init;

import com.store.goodsstore.dto.RegistrationRequest;
import com.store.goodsstore.entities.Goods;
import com.store.goodsstore.entities.GoodsGroup;
import com.store.goodsstore.entities.Store;
import com.store.goodsstore.repository.GoodsRepository;
import com.store.goodsstore.repository.GroupRepository;
import com.store.goodsstore.repository.StoreRepository;
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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private GoodsRepository goodsRepository;

    @PostConstruct
    public void saveAcount() {
        RegistrationRequest request = createRequest();
        if (!userRepository.existsByEmail(request.getUserEmail())) {
            service.register(createRequest());
            Store store = storeRepository.findByName("FirstStore");

            GoodsGroup group = new GoodsGroup();
            store.addGroup(group);
            group.setName("testGroup");
            group.setStore(store);
            groupRepository.save(group);

            Goods goods = new Goods();
            goods = new Goods();
            goods.setCode("1");
            goods.setName("goods1");
            goods.setUnit("kg");
            goods.setGroup(groupRepository.findByName("testGroup"));
            goodsRepository.save(goods);

        }

    }

    public RegistrationRequest createRequest() {
        RegistrationRequest request = new RegistrationRequest();
        request.setOrganizationName("TestStore");
        request.setOrganizationEmail("y.shemanska@gmail.com");
        request.setStoreName("FirstStore");
        request.setUserEmail("y.shemanska@gmail.com");
        request.setUserName("Me");
        request.setUserPass("123");
        return request;
    }

}
