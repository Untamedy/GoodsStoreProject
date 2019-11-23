package com.store.goodsstore.services;

import com.store.goodsstore.dto.RegistrationRequest;
import com.store.goodsstore.dto.StoreDto;
import com.store.goodsstore.entities.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.store.goodsstore.repository.StoreRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 *
 * @author YBolshakova
 */
@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepositary;
    
    @Autowired
    private GoodsService goodsService;
    
    @Autowired
    private OrganizationService orgService;

    public StoreDto editStore(RegistrationRequest request) {
        StoreDto dto = null;
        Store store = storeRepositary.findByOrg(orgService.getByEmail(request.getOrganizationEmail()).getId());
        if (store != null) {
            store.setName(request.getStoreName());           
            dto = createStoreDto(storeRepositary.save(store));
        }
        return dto;
    }

    public boolean deleteStore(StoreDto storeDto) {
        if (storeRepositary.existsByCode(storeDto.getCode())) {
            Store store = storeRepositary.findByCode(storeDto.getName());
            if (store.getGroups().isEmpty()) {
                 storeRepositary.delete(store);
                return true;
            }           
        }
        return false;
    }

    public Store saveStore(RegistrationRequest request) {
        Store store = storeRepositary.findByName(request.getStoreName());
        if (null == store) {
            store = new Store();
            store.setName(request.getStoreName());
            store.setCode(createIdentifier());
            return storeRepositary.save(store);
        }
        throw new RuntimeException("Store with name " + request.getStoreName() + " is already exists");
    }

    public StoreDto createStoreDto(Store store) {
        StoreDto response = new StoreDto();
        response.setCode(store.getCode());
        response.setName(store.getName());
        response.setOrganization(store.getOrg());
        response.setDescription(store.getDescription());
        return response;
    }
   

    public Store getById(int id) {
        return storeRepositary.getOne(id);
    }

    public Store getByCode(String storeCode) {
        return storeRepositary.findByCode(storeCode);
    }

    public String createIdentifier() {
        return UUID.randomUUID().toString();
    }

}
