package com.services;

import com.dto.RegistrationRequest;
import com.dto.RegistrationResponse;
import com.dto.StoreRequest;
import com.dto.StoreResponse;
import com.entities.Organization;
import com.entities.Store;
import com.repository.StoreRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author YBolshakova
 */
@Service
public class StoreService {

    @Autowired
    private StoreRepositary storeRepositary;
    @Autowired
    GoodsService goodsService;

    public StoreResponse saveStore(StoreRequest storeRequest) {
        if (!storeRepositary.existsByName(storeRequest.getName())) {
            Store store = createStore(storeRequest);            
            storeRepositary.save(store);
            return createStoreResponse(store);
        }
        throw new RuntimeException("Store is not created");
    }

    public boolean deleteStore(StoreRequest storeRequest) {       
        if (storeRepositary.existsByName(storeRequest.getName())) {
            Store store = storeRepositary.findByName(storeRequest.getName());
            if(goodsService.goodsCount(store.getId())> 0){
                return false;                
            }                      
            storeRepositary.delete(store);            
        }
        return true;
    }

    public StoreResponse editStore(StoreRequest storeRequest) {
        if(storeRepositary.existsByStoreCode(storeRequest.getCode())){
           Store store = storeRepositary.updateStore(storeRequest.getCode());
           return createStoreResponse(store);                   
        }     
        throw new RuntimeException("Store with code " + storeRequest.getCode() + " isn't exist");
    }

    public Store createStore(StoreRequest storeRequest) {
        Store store = new Store();
        store.setName(storeRequest.getName());
        store.setDescription(storeRequest.getDescription());
        return store;
    }
    
    public StoreRequest createStoreRequest(RegistrationRequest request, Organization organization){
        StoreRequest storeRequest = new StoreRequest();
        storeRequest.setCode(request.getStoreCode());
        storeRequest.setName(request.getStoreName());
        storeRequest.setDescription(request.getStoreDiscription());
        return storeRequest;
        
    }
    
    public StoreResponse createStoreResponse(Store store){
        StoreResponse response = new StoreResponse();
        response.setName(store.getName());        
        return response;
    }
    
    public Page<StoreResponse> getAllStore( int pages, int size, Integer orgId){
        Pageable page = PageRequest.of(pages, size);
        Page<StoreResponse> allStore = storeRepositary.findByOrgId(orgId, page).map((s) -> {
            return createStoreResponse(s); 
        });
         return allStore;       
    }

    

}
