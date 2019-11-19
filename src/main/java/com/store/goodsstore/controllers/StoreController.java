package com.store.goodsstore.controllers;

import com.store.goodsstore.dto.StoreDto;
import com.store.goodsstore.services.StoreService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author YBolshakova
 */
@RestController
@RequestMapping("/admin/store")
public class StoreController {

    @Autowired
    StoreService storeServiсe;

  
    @PostMapping("/edit")
    public ModelAndView editStore(@RequestBody StoreDto storeDto, Map<String, StoreDto> model) {
        StoreDto dto = storeServiсe.editStore(storeDto);
        if(dto!=null){
         model.put("editStore",dto ); 
         return new ModelAndView("store", model, HttpStatus.OK);
        }   
        return new ModelAndView("storePage", HttpStatus.NOT_MODIFIED);
        
    }    

}
