/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.services;

import com.store.goodsstore.dto.CustomerDto;
import com.store.goodsstore.entities.Customer;
import com.store.goodsstore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lenovo
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private IncomDocService incomeService;

    public CustomerDto saveCustomer(CustomerDto dto) {
        return cretateDto(repository.save(createCustomer(dto)));
    }

    public boolean deleteCustomer(String phone) {
        Customer customer = repository.findByPhoneNum(phone);
        if (orderService.getByCustomer(phone).isEmpty() && incomeService.getByCustomer(phone).isEmpty()) {
            repository.delete(customer);
            return true;
        }
        return false;
    }

    public Customer getCustomerByName(String customer) {
        return repository.findByName(customer);

    }

    public Customer getCustomerByPhone(String phone) {
        return repository.findByPhoneNum(phone);
    }

    public Customer createCustomer(CustomerDto dto) {
        Customer customer = repository.findByPhoneNum(dto.getPhone());
        if (customer == null) {
            customer = new Customer();
        }
        customer.setName(dto.getName());
        customer.setOrg(organizationService.getByName(dto.getName()));
        customer.setPhoneNum(dto.getPhone());

        return customer;
    }

    public CustomerDto cretateDto(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.setName(customer.getName());
        dto.setPhone(customer.getPhoneNum());
        dto.setOrgName(customer.getOrg().getName());
        return dto;
    }

}
