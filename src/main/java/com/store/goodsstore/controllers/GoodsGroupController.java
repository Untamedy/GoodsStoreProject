/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.controllers;

import com.store.goodsstore.dto.GoodsGroupDto;
import com.store.goodsstore.services.GoodsGroupService;
import com.store.goodsstore.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ModelAndView saveGroup(@RequestParam("groupName") String name, @RequestParam("storeCode") String code) {
        GoodsGroupDto dto = new GoodsGroupDto();
        dto.setName(name);
        dto.setStoreCode(code);
        service.saveGroup(dto);
        return new ModelAndView("redirect:/gostore/"+code);

    }

    @PostMapping("/editGroup")
    public ModelAndView editGroup(@RequestParam("newName") String newName, @RequestParam("oldName") String oldName, @RequestParam("storeCode") String storeCode) {
        service.editGroup(newName, oldName, storeCode);
        return new ModelAndView("redirect:/gostore/"+storeCode);

    }

    @PostMapping("/removedGroup")
    public ModelAndView removeGroup(@RequestParam("name") String name,@RequestParam("storeCode")String code) {
        service.removeGroup(name,code);
        return new ModelAndView("redirect:/gostore/"+code);
    }

}
