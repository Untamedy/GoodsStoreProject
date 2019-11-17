/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.controllers;

import com.store.goodsstore.dto.GoodsGroupDto;
import com.store.goodsstore.services.GoodsGroupService;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Lenovo
 */
@Controller
public class GoodsGroupController {

    @Autowired
    private GoodsGroupService service;

    @GetMapping("/groups")
    public ModelAndView getAllGroups(Model model) {
        return new ModelAndView("storePage", "grops", service.getAll());

    }
    
    @PostMapping("/saveGroup{name}")
    public ModelAndView saveGroup(@PathVariable String name){
       return new ModelAndView("storePage","newGroup", service.saveGroup(name));
           
       }
    
    @PostMapping("editGroup")
    public ModelAndView editGroup(@PathVari)
        
    }


