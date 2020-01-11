/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.controllers;

import com.store.goodsstore.dto.IncomeDocDto;
import com.store.goodsstore.dto.OrderDto;
import com.store.goodsstore.entities.Organization;
import com.store.goodsstore.services.IncomDocService;
import com.store.goodsstore.services.OrderService;
import com.store.goodsstore.services.OrganizationService;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ReportsController {

    private static final Logger logger = LoggerFactory.getLogger(ReportsController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private IncomDocService incomeService;
    @Autowired
    private OrganizationService orgService;

    @PostMapping("/saleGoodsReport")
    public ModelAndView createSaleReport(@RequestParam("dateFrom") Date dateFrom, @RequestParam("dateTo") Date dateTo,@RequestParam("orgCode")String code) {
        ModelAndView model = new ModelAndView("saleReportPage");
        Organization organization = orgService.getByCode(code);
        List<OrderDto> orders = orderService.getByPeriod(dateFrom, dateTo,organization);
        model.addObject("list", orders);
        return model;
    }

    @PostMapping("/incomeGoodsReport")
    public ModelAndView createAddGoodsCountrepost(@RequestParam("dateFrom") Date dateFrom, @RequestParam("dateTo") Date dateTo,@RequestParam("orgCode")String code) {
         ModelAndView model = new ModelAndView("inputReportPage");
          Organization organization = orgService.getByCode(code);
        List<IncomeDocDto> incomes = incomeService.getByPeriod(dateFrom, dateTo,organization);
        model.addObject("list", incomes);
        return model;
    }

    @PostMapping("/finReport")
    public ModelAndView createFinReport(@RequestParam("dateFrom") Date dateFrom, @RequestParam("dateTo") Date dateTo,@RequestParam("orgCode")String code) {
        ModelAndView model = new ModelAndView("finReportPage");
        Organization organization = orgService.getByCode(code);
        List<OrderDto> orders = orderService.getByPeriod(dateFrom, dateTo,organization);
        double orderSum = 0;
        for (OrderDto order : orders) {
            orderSum += order.getOrderSum();
        }
        List<IncomeDocDto> incomes = incomeService.getByPeriod(dateFrom, dateTo,organization);
        double incomeSum = 0;
        for (IncomeDocDto in : incomes) {
            incomeSum += in.getSum();
        }
        model.addObject("orderSum", orderSum);
        model.addObject("incomeSum", incomeSum);
        model.addObject("result", orderSum - incomeSum);
        return model;
    }
    
    @GetMapping("/search/{orgCode}")
    public ModelAndView searchOrder(@RequestParam("customer") String num,@PathVariable("orgCode")String orgCode) {
        List<OrderDto> orders = orderService.getByCustomer(num,orgCode);
        ModelAndView model = new ModelAndView("saleReportPage");
        model.addObject("list", orders);
        return model;
    }

}
