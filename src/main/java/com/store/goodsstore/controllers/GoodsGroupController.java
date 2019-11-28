/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.controllers;


import com.store.goodsstore.services.GoodsGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
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
    public ModelAndView getAllGroups() {
        return new ModelAndView("storePage", "grops", service.getAllGroupDto());

    }

    @PostMapping("/saveGroup")
    public ModelAndView saveGroup(@RequestParam String name) {
        return new ModelAndView("store", "newGroup", service.saveGroup(name));

    }

    @PostMapping("editGroup/{oldName}/{newName}")
    public ModelAndView editGroup(@PathVariable("newName") String newName, @PathVariable("oldName")String oldName) {
        return new ModelAndView("storePage", "editGroup", service.editGroup(newName,oldName));

    }

    @GetMapping("/removedGroup/{name}")
    public ModelAndView removeGroup(@RequestParam String name) {
        if (service.removeGroup(name)) {            
            return new ModelAndView("storePage",HttpStatus.OK);
        }
        return new ModelAndView("storePage", HttpStatus.NOT_MODIFIED);
    }

}
