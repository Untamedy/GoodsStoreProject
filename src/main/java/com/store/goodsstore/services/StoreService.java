package com.store.goodsstore.services;

import com.store.goodsstore.dto.RegistrationRequest;
import com.store.goodsstore.dto.StoreDto;
import com.store.goodsstore.entities.Organization;
import com.store.goodsstore.entities.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.store.goodsstore.repository.StoreRepository;
import java.util.List;
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

    public StoreDto saveStore(StoreDto storeDto) {        
        if (!storeRepositary.existsByName(storeDto.getName())) {
            Store store = createStore(storeDto);
            return createStoreDto(storeRepositary.save(store));
        }
        return null;
    }

    public boolean deleteStore(StoreDto storeDto) {
        if (storeRepositary.existsByName(storeDto.getName())) {
            Store store = storeRepositary.findByName(storeDto.getName());
            if (goodsService.goodsCount(store.getId()) > 0) {
                return false;
            }
            storeRepositary.delete(store);
        }
        return true;
    }  

    public Store createStore(StoreDto storeDto) {
        Store store = new Store();
        store.setName(storeDto.getName());
        store.setDescription(storeDto.getDescription());
        return store;
    }

    public StoreDto createStoreRegistrRequest(RegistrationRequest request, Organization organization) {
        StoreDto storeDto = new StoreDto();
        storeDto.setName(request.getStoreName());
        storeDto.setOrganization(organization);
        return storeDto;

    }

    public StoreDto createStoreDto(Store store) {
        StoreDto response = new StoreDto();
        response.setName(store.getName());
        response.setOrganization(store.getOrg());
        response.setDescription(store.getDescription());
        return response;
    }

    public List<StoreDto> getAllStore(Integer orgId) {
        List<Store> allStore = storeRepositary.findByOrg(orgId);
        List<StoreDto> allStoreDto;
        allStoreDto = allStore.stream().map(this::createStoreDto).collect(Collectors.toList());
        return allStoreDto;
    }

    
    public Store getById(int id){
        return storeRepositary.getOne(id);
    }
    
}
