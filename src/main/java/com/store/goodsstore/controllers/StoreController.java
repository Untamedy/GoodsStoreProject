package com.store.goodsstore.controllers;

import com.store.goodsstore.dto.StoreDto;
import com.store.goodsstore.services.StoreService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @PostMapping("/save")
    @ExceptionHandler({RuntimeException.class})
    public ModelAndView saveNewStore(@RequestBody StoreDto request, Map<String, StoreDto> model) {
        model.put("newStore", storeServiсe.saveStore(request));
        return new ModelAndView("store", model, HttpStatus.OK);
    }

    @PostMapping("/remove")
    @ExceptionHandler({RuntimeException.class})
    public ModelAndView deleteStore(@RequestBody StoreDto request) {
        if (storeServiсe.deleteStore(request)) {
            return new ModelAndView("storePage", HttpStatus.OK);
        }
        return new ModelAndView("storePage", HttpStatus.NOT_MODIFIED);
    }

    @PostMapping("/edit")
    public ModelAndView editStore(@RequestBody StoreDto storeDto, Map<String, StoreDto> model) {
        model.put("newStore", storeServiсe.saveStore(storeDto));
        return new ModelAndView("store", model, HttpStatus.OK);
    }

    @GetMapping("/allStores?pages={pages}&size={size}&id={id}")
    @ExceptionHandler({RuntimeException.class})
    public List<StoreDto> getAllStores(@PathVariable("id") Integer id) {
        return storeServiсe.getAllStore(id);
    }

}
