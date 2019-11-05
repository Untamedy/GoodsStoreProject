package com.controllers;

import com.dto.StoreRequest;
import com.dto.StoreResponse;
import com.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author YBolshakova
 */
@RestController
@RequestMapping("/store")
public class StoreController {
    @Autowired
    StoreService storeServiсe;
    
    @RequestMapping(method = RequestMethod.POST, value = "/save")
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<StoreResponse> saveNewStore(@RequestBody StoreRequest request){
      return new ResponseEntity<>(storeServiсe.saveStore(request),HttpStatus.OK);  
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/remove")
    public ResponseEntity<String> deleteStore(@RequestBody StoreRequest request){
        if(storeServiсe.deleteStore(request)){
           return new ResponseEntity<>("Store " + request.getName() + " remove successful", HttpStatus.OK); 
        }
         return new ResponseEntity<>("Store " + request.getName() + " contains goods", HttpStatus.NOT_MODIFIED);                
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/allStores?pages={pages}&size={size}&id={id}")
    public Page<StoreResponse> getAllStores(@PathVariable ("pages") int pages, @PathVariable ("size") int size, @PathVariable ("id") Integer id){
       return storeServiсe.getAllStore(pages, size, id);        
    }
    
    


}
