package com.store.goodsstore.services;

import com.store.goodsstore.dto.RegistrationRequest;
import com.store.goodsstore.dto.StoreDto;
import com.store.goodsstore.entities.Organization;
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

    public StoreDto saveStore(StoreDto storeDto) {        
        if (!storeRepositary.existsByCode(storeDto.getCode())) {
            Store store = createStore(storeDto);
            return createStoreDto(storeRepositary.save(store));
        }
        return null;
    }

    public boolean deleteStore(StoreDto storeDto) {
        if (storeRepositary.existsByCode(storeDto.getCode())) {
            Store store = storeRepositary.findByCode(storeDto.getName());
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
        store.setCode(storeDto.getCode());
        return store;
    }

    public StoreDto createStoreRegistrRequest(RegistrationRequest request, Organization organization) {
        StoreDto storeDto = new StoreDto();
        storeDto.setName(request.getStoreName());
        storeDto.setOrganization(organization);
        storeDto.setCode(createIdentifier());
        return storeDto;

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

    
    public Store getById(int id){
        return storeRepositary.getOne(id);
    }
    
     public String createIdentifier() {
         return UUID.randomUUID().toString();        
    }

    public Store getByCode(String storeCode) {
        return storeRepositary.findByCode(storeCode);
    }
    
}
