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
    GoodsService goodsService;

    public StoreDto editStore(StoreDto requestDto) {
        StoreDto dto = null;
        Store store = storeRepositary.findByCode(requestDto.getCode());
        if (store != null) {
            store.setName(requestDto.getName());
            store.setDescription(requestDto.getDescription());
            dto = createStoreDto(storeRepositary.saveAndFlush(store));
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

    public Store createStore(RegistrationRequest request) {
        Store store = storeRepositary.findByName(request.getStoreName());
        if (null != store) {
            store = new Store();
            store.setName(request.getStoreName());
            store.setCode(createIdentifier());
        }
        return store;
    }

    public StoreDto createStoreDto(Store store) {
        StoreDto response = new StoreDto();
        response.setCode(store.getCode());
        response.setName(store.getName());
        response.setOrganization(store.getOrg());
        response.setDescription(store.getDescription());
        return response;
    }

    public List<StoreDto> getAllStore(Integer orgId) {
        return storeRepositary.findByOrg(orgId).stream().map(this::createStoreDto).collect(Collectors.toList());
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
