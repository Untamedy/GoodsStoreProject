package com.store.goodsstore.init;

import com.store.goodsstore.dto.RegistrationRequest;
import com.store.goodsstore.entities.Goods;
import com.store.goodsstore.entities.GoodsCounter;
import com.store.goodsstore.entities.GoodsGroup;
import com.store.goodsstore.entities.GoodsIncomePrice;
import com.store.goodsstore.entities.GoodsPrice;
import com.store.goodsstore.entities.Store;
import com.store.goodsstore.repository.GoodsRepository;
import com.store.goodsstore.repository.GroupRepository;
import com.store.goodsstore.repository.StoreRepository;
import com.store.goodsstore.repository.UserRepository;
import com.store.goodsstore.services.RegistrationService;
import java.util.ArrayList;
import java.util.List;
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
        }
        Store store = storeRepository.findByName("FirstStore");
        if (groupRepository.findByNameAndStoreId("testGroup1", store.getId()) == null) {
            for(int i =1; i<10;i++){
                addGroups(store, i);
            }            
        }
    }

    private void addGroups(Store store, int index) {       
            GoodsGroup group = new GoodsGroup();
            group.setName("testGroup" + index);
            group.setStore(store);
            List<Goods> goods = addGoods(group);
            group.setGoods(goods);
            groupRepository.save(group);
            goodsRepository.saveAll(goods);
            }

    private List<Goods> addGoods(GoodsGroup group) {
        List<Goods> list = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            Goods goods = new Goods();
            goods = new Goods();
            goods.setCode("code" + i + group.getName());
            goods.setName("goods" + i);
            goods.setUnit("kg");
            goods.setCounter(new GoodsCounter(i));
            goods.setIncomePrice(new GoodsIncomePrice(i));
            goods.setPrice(new GoodsPrice(i));
            goods.setGroup(group);
            list.add(goods);
        }

        return list;
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
