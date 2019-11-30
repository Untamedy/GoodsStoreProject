/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.controllers;



import com.store.goodsstore.dto.GoodsGroupDto;
import com.store.goodsstore.services.GoodsGroupService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/all")
    public ModelAndView getAllGroups() {
        return new ModelAndView("store", "grops", service.getAllGroupDto());

    }

    @GetMapping("/save")
    public ModelAndView saveGroup(@RequestParam("groupName") String name,@RequestParam("storeCode")String code) {
        GoodsGroupDto dto = new GoodsGroupDto();
        
        dto.setName(name);
        dto.setStoreCode(code);
        return new ModelAndView("storePage", "newGroup", service.saveGroup(dto));

    }

    @GetMapping("editGroup")
    public ModelAndView editGroup(@RequestParam("newName") String newName, @RequestParam("oldName")String oldName) {        
        return new ModelAndView("storePage", "editGroup", service.editGroup(newName,oldName));

    }

    @GetMapping("/removedGroup")
    public ModelAndView removeGroup(@RequestParam String name) {
        if (service.removeGroup(name)) {            
            return new ModelAndView("storePage",HttpStatus.OK);
        }
        return new ModelAndView("storePage", HttpStatus.NOT_MODIFIED);
    }

}
