/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.services;

import com.store.goodsstore.dto.GoodsDto;
import com.store.goodsstore.dto.OrderDto;
import com.store.goodsstore.entities.Goods;
import com.store.goodsstore.entities.Order;
import com.store.goodsstore.entities.Organization;
import com.store.goodsstore.repository.OrderRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lenovo
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrganizationService organizationService;

    @Transactional
    public OrderDto saveOrder(OrderDto dto) {
        return createDto(repository.save(createOrder(dto)));
    }

    public Order createOrder(OrderDto dto) {
        Organization organisation = organizationService.getByCode(dto.getOrgCode());
        Order order = repository.findByNumAndOrgId(dto.getOrderNum(), organisation.getId());
        if (order == null) {
            order = new Order();
        }
        order.setCustomer(customerService.getCustomerByPhoneAndOrgCode(dto.getCustomerPhone(), dto.getOrgCode()));
        Set<Goods> goods = dto.getGoods().stream().map((tmp) -> {
            return goodsService.createGoods(tmp);
        }).collect(Collectors.toSet());
        order.setGoods(goods);
        order.setDate(dto.getOrderDate());
        order.setNum(dto.getOrderNum());
        order.setOrg(organizationService.getByName(dto.getOrgName()));
        order.setSum(dto.getOrderSum());
        return order;
    }

    private OrderDto createDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setCustomerName(order.getCustomer().getName());
        dto.setCustomerPhone(order.getCustomer().getPhoneNum());
        dto.setOrderNum(order.getNum());
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
        return createDto(repository.findByNum(docNum));
    }

    @Transactional
    public List<Order> getByCustomer(String phone, String code) {
        return repository.findByCustomer(customerService.getCustomerByPhoneAndOrgCode(phone, code).getId());
    }

    @Transactional
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
}
