/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.controllers;

import com.store.goodsstore.dto.CustomerDto;
import com.store.goodsstore.dto.OrganizationDto;
import com.store.goodsstore.services.CustomerService;
import com.store.goodsstore.services.OrganizationService;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired
    private OrganizationService orgService;
    
    @GetMapping("/allCustomer")
    public ModelAndView redirectToAllCuctomerController(Principal principal){
        OrganizationDto dto = orgService.getOrgData(principal);
        return new ModelAndView("redirect:/customer/allCustomer/page/"+dto.getOrgCode()+"/1");
    }
    
    @GetMapping("/allCustomer/page/{orgCode}/{page}")
    public ModelAndView getGoodsByGroupId(@PathVariable("orgCode")String code, @PathVariable("page") int page) {
        ModelAndView model = new ModelAndView("сustomerPage");
        PageRequest pageable = PageRequest.of(page - 1, 10);
        Page<CustomerDto> customerPage = customerService.getPaginatedCustomer(code, pageable);
        int totalPage = customerPage.getTotalPages();
        if (totalPage > 0) {
            List<Integer> pageNunbers = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addObject("pageNumber", pageNunbers);
        }       
        model.addObject("customerList", customerPage.getContent());

        return model;
    }


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
    public ModelAndView deleteCustomer(@RequestParam("name")String name, @RequestParam("orgCode")String code){
        customerService.deleteCustomer(name, code);
        return new ModelAndView("customerPage");
    }
    
    @PostMapping("/edit")
    public ModelAndView editCustomer(@ModelAttribute("customer")CustomerDto dto){        
        customerService.editCustomer(dto);        
        return new ModelAndView("customerPage");        
    }
        
  

}