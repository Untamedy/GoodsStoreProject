/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.goodsstore.services;

import com.store.goodsstore.dto.CustomerDto;
import com.store.goodsstore.entities.Customer;
import com.store.goodsstore.entities.Organization;
import com.store.goodsstore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public CustomerDto saveCustomer(CustomerDto dto) {
        return cretateDto(repository.save(createCustomer(dto)));
    }

    @Transactional
    public boolean deleteCustomer(String phone, String orgCode) {
        Customer customer = repository.findByPhoneNumAndOrgCode(phone, orgCode);
        if (orderService.getByCustomer(phone, orgCode).isEmpty() && incomeService.getByCustomer(phone, orgCode).isEmpty()) {
            repository.delete(customer);
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public Customer getCustomerByName(String customer) {
        return repository.findByName(customer);

    }

    @Transactional(readOnly = true)
    public Customer getCustomerByPhoneAndOrgCode(String phone, String code) {
        return repository.findByPhoneNumAndOrgCode(phone, code);
    }

    @Transactional
    public CustomerDto editCustomer(CustomerDto dto) {
        Customer customer = repository.findByPhoneNumAndOrgCode(dto.getPhone(), dto.getOrgCode());
        customer.setName(dto.getName());
        repository.save(customer);
        return cretateDto(customer);
    }

    public Customer createCustomer(CustomerDto dto) {
        Customer customer = repository.findByPhoneNumAndOrgCode(dto.getPhone(), dto.getOrgCode());
        if (customer == null) {
            customer = new Customer();
            customer.setName(dto.getName());
            customer.setOrg(organizationService.getByCode(dto.getOrgCode()));
            customer.setPhoneNum(dto.getPhone());
        }
        return customer;
    }

    public CustomerDto cretateDto(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.setName(customer.getName());
        dto.setPhone(customer.getPhoneNum());
        dto.setOrgCode(customer.getOrg().getCode());
        return dto;
    }
 @Transactional
    public Page<CustomerDto> getPaginatedCustomer(String orgcode, Pageable page) {
        Organization org = organizationService.getByCode(orgcode);
        Page<Customer> goods = repository.findAllByOrgId(org.getId(), page);
        Page<CustomerDto> customerDto = goods.map(this::cretateDto);
        return customerDto;
    }

}
