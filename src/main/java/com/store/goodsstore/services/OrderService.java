/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.services;

import com.store.goodsstore.dto.GoodsDto;
import com.store.goodsstore.dto.OrderDto;
import com.store.goodsstore.entities.Customer;
import com.store.goodsstore.entities.Goods;
import com.store.goodsstore.entities.Order;
import com.store.goodsstore.entities.Organization;
import com.store.goodsstore.repository.OrderRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lenovo
 */
@Service
public class OrderService {
      private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository repository;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrganizationService organizationService;

    @Transactional
    public OrderDto saveOrder(OrderDto order) {
        return createDto(repository.save(createOrder(order)));
    }

    public Order createOrder(OrderDto order) {
        Organization organisation = organizationService.getByCode(order.getOrgCode());
        Customer customer = customerService.getCustomerByPhoneAndOrgCode(order.getCustomerPhone(),order.getOrgCode());      
        Order newOrder = new Order();        
        newOrder.setCustomer(customer);
        List<Goods> goods = order.getGoods().stream().map((tmp) -> {
            return goodsService.createGoods(tmp);
        }).collect(Collectors.toList());
        newOrder.setGoods(goods);
        newOrder.setDate(new Date());        
        newOrder.setOrg(organisation);
        newOrder.setSum(countSum(order.getGoods()));
        logger.debug("Order created");
        return newOrder;
    }

    private OrderDto createDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setCustomerName(order.getCustomer().getName());
        dto.setCustomerPhone(order.getCustomer().getPhoneNum());
        dto.setOrderNum(order.getId());
        dto.setOrgName(order.getOrg().getName());
        dto.setOrgCode(order.getOrg().getCode());
        dto.setOrderDate(order.getDate());
        List<GoodsDto> goodsDto = order.getGoods().stream().map((tmp) -> {
            GoodsDto gDto = goodsService.createGoodsResponse(tmp);
            return gDto;
        }).collect(Collectors.toList());
        dto.setGoods(goodsDto);
        dto.setOrderSum(order.getSum());
        return dto;
    }

    @Transactional
    public OrderDto getByNum(String docNum) {
        return createDto(repository.findById(Integer.valueOf(docNum)).get());
    }

    @Transactional
    public List<Order> getByCustomer(Customer customer) {
        return repository.findByCustomerId(customer.getId());
    }

    @Transactional(readOnly = true)
    public List<OrderDto> getByPeriod(Date dateFrom, Date dateTo) {
        List<OrderDto> dtoList = new ArrayList<>();
        List<Order> list = repository.findByDate(dateFrom, dateTo);
        if (!list.isEmpty()) {
            dtoList = list.stream().map((tmp) -> {
                return createDto(tmp);
            }).collect(Collectors.toList());
            return dtoList;
        }
        return dtoList;
    }
    
     public String givenNum() {
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;
        return RandomStringUtils.random(length, useLetters, useNumbers);

    }
     
     private double countSum(List<GoodsDto> goods){
         int sum = 0;
         for(GoodsDto g:goods){
            sum+=g.getPrice();
         }
         return sum;
     }
}
