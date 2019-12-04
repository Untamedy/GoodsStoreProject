/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.controllers;

import com.store.goodsstore.dto.UserDto;
import com.store.goodsstore.entities.Organization;
import com.store.goodsstore.entities.Store;
import com.store.goodsstore.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.store.goodsstore.services.StoreService;
import com.store.goodsstore.services.UserService;
import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Lenovo
 */
@Controller
public class StoreController {

    @Autowired
    private StoreService service;

    @Autowired
    private UserService usersevice;

    @Autowired
    private OrganizationService orgService;
    
    @Autowired
    private GoodsGroupService groupService;

    @GetMapping("/store")
    public ModelAndView toStorepage() {
        ModelAndView model = new ModelAndView("storePage");
        return model;

    }

    @PostMapping("/store")
    public ModelAndView goWork() {
        return new ModelAndView("storeListPage");
    }

    @GetMapping("/gostore")
    public ModelAndView allStore(Principal principal) {
        String name = principal.getName();
        UserDto user = usersevice.getUsersByEmail(name);
        Organization org = user.getOrganization();
        List<Store> stores = org.getStore();
        Store store = stores.get(0);
        List<GoodsGroupDto> groups = store.getGroups().stream().map((tmp)->{
            groupService.cre
        })
                
        ModelAndView model = new ModelAndView("storePage");
        model.addObject("groups",);
        return model;

    }
}
