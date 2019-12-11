/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.controllers;

import com.store.goodsstore.dto.GoodsGroupDto;
import com.store.goodsstore.dto.UserDto;
import com.store.goodsstore.entities.Organization;
import com.store.goodsstore.entities.Store;
import com.store.goodsstore.services.GoodsGroupService;
import com.store.goodsstore.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.store.goodsstore.services.StoreService;
import com.store.goodsstore.services.UserService;
import java.security.Principal;
import java.util.List;
import java.util.Set;
import org.apache.tomcat.jni.SSLContext;
import org.springframework.web.bind.annotation.ModelAttribute;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;



/**
 *
 * @author Lenovo
 */
@Controller
@SessionAttribute
public class StoreController {

    @Autowired
    private StoreService service;

    @Autowired
    private UserService usersevice;

    @Autowired
    private OrganizationService orgService;
    
    @Autowired
    private GoodsGroupService groupService;
    @Autowired
    private UserService userService;
    
    @ModelAttribute("storeCode")
    public String code(Principal principal){
        String name = principal.getName();
        UserDto user = userService.getUsersByEmail(name);
        Organization org = user.getOrganization();
        Store store = org.getStore();
        return store.getCode();
    }

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
        List<GoodsGroupDto> groups = service.getGroupListByCurentStore(principal);                
        ModelAndView model = new ModelAndView("storePage");
        
        model.addObject("groups",groups);
        return model;

    }
}
