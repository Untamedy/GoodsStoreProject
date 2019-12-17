/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.controllers;

import com.store.goodsstore.dto.CustomerDto;
import com.store.goodsstore.entities.Customer;
import com.store.goodsstore.services.CustomerService;
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
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    public ModelAndView saveCustomer(@RequestParam("name") String name, @RequestParam("phoneNum") String phoneNum, @RequestParam("orgCode") String code) {
        CustomerDto dto = new CustomerDto();
        dto.setName(name);
        dto.setPhone(phoneNum);
        dto.setOrgCode(code);
        customerService.saveCustomer(dto);
        return new ModelAndView("customerPage");
    }
    
    @GetMapping("/delete")
    public ModelAndView deleteCustomer()

}
