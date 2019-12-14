/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.controllers;



import com.store.goodsstore.dto.GoodsGroupDto;
import com.store.goodsstore.services.GoodsGroupService;
import com.store.goodsstore.services.StoreService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Lenovo
 */
@Controller
@RequestMapping("/group")
public class GoodsGroupController {

    @Autowired
    private GoodsGroupService service;
    @Autowired
    private StoreService storeService;

   

    @PostMapping("/save")
    public ModelAndView saveGroup(@RequestParam("groupName") String name,@RequestParam("storeCode")String code) {
        GoodsGroupDto dto = new GoodsGroupDto();        
        dto.setName(name);
        dto.setStoreCode(code);
        service.saveGroup(dto);       
        return new ModelAndView("redirect:/gostore");

    }

    @PostMapping("/editGroup")
    public ModelAndView editGroup(@RequestParam("newName") String newName, @RequestParam("oldName")String oldName,@RequestParam("storeCode") String storeCode) {        
       service.editGroup(newName,oldName,storeCode);
        return new ModelAndView("redirect:/gostore");

    }

   /* @GetMapping("/removedGroup")
    public ModelAndView removeGroup(@RequestParam String name) {
        if (service.removeGroup(name)) {            
            return new ModelAndView("storePage",HttpStatus.OK);
        }
        return new ModelAndView("storePage", HttpStatus.NOT_MODIFIED);
    }*/

}
