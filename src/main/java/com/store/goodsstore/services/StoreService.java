package com.store.goodsstore.services;

import com.store.goodsstore.dto.GoodsGroupDto;
import com.store.goodsstore.dto.RegistrationRequest;
import com.store.goodsstore.dto.StoreDto;
import com.store.goodsstore.entities.Store;
import com.store.goodsstore.exceptions.RegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.store.goodsstore.repository.StoreRepository;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author YBolshakova
 */
@Service
public class StoreService {
    
     private static final Logger logger = LoggerFactory.getLogger(StoreService.class);

    @Autowired
    private StoreRepository storeRepositary;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrganizationService orgService;

    @Autowired
    private GoodsGroupService groupService;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveStore(Store store) {
        storeRepositary.save(store);
    }

    @Transactional
    public StoreDto editStore(RegistrationRequest request) {
        StoreDto dto = null;
        Store store = storeRepositary.findByOrg(orgService.getByEmail(request.getOrganizationEmail()).getId());
        if (store != null) {
            store.setName(request.getStoreName());
            dto = createStoreDto(storeRepositary.save(store));
            logger.debug("Store " + request.getStoreName() +" edited");
            
        }
        return dto;
    }

    @Transactional
    public boolean deleteStore(StoreDto storeDto) {
        if (storeRepositary.existsByCode(storeDto.getCode())) {
            Store store = storeRepositary.findByCode(storeDto.getName());
            if (groupService.getAllGroupByStore(store.getId()).isEmpty()) {
                storeRepositary.delete(store);
                logger.debug("Store"+ storeDto.getName()+ "deleted");
                return true;
            }
        }
        return false;
    }

    public Store createStore(RegistrationRequest request) {
        Store store = storeRepositary.findByName(request.getStoreName());
        if (null == store) {
            store = new Store();
            store.setName(request.getStoreName());
            store.setCode(createIdentifier());
            return store;
        }
        throw new RegistrationException("Store with name " + request.getStoreName() + " is already exists");
    }

    public StoreDto createStoreDto(Store store) {
        StoreDto response = new StoreDto();
        response.setCode(store.getCode());
        response.setName(store.getName());
        response.setOrganization(store.getOrg());
        response.setDescription(store.getDescription());
        return response;
    }

    @Transactional(readOnly = true)
    public Store getById(int id) {
        return storeRepositary.getOne(id);
    }

    @Transactional(readOnly = true)
    public Store getByCode(String storeCode) {
        return storeRepositary.findByCode(storeCode);
    }

    private String createIdentifier() {
        return UUID.randomUUID().toString();
    }

    @Transactional(readOnly = true)
    public List<GoodsGroupDto> getGroupListByCurentStore(String storeCode) { 
        Store store = storeRepositary.findByCode(storeCode);
        List<GoodsGroupDto> groups = groupService.getAllGroupByStore(store.getId());
        logger.debug("Selected all groups by store");
        return groups;
    }

}
