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
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private IncomDocService incomeService;

    @Transactional
    public Customer saveCustomer(CustomerDto dto) {
        if (repository.existsByPhoneNum(dto.getPhone())) {
            throw new RuntimeException("Customer with phone number " + dto.getPhone() + "is already exists");
        }
        logger.debug("Customer " + dto.getName() + " saved");
        Customer cust = createCustomer(dto);
        return repository.save(cust);
    }

    @Transactional
    public boolean deleteCustomer(String phone, String orgCode) {
        Customer customer = repository.findByPhoneNumAndOrgCode(phone, orgCode);
        if (orderService.getByCustomer(phone, orgCode).isEmpty() && incomeService.getByCustomer(customer).isEmpty()) {
            repository.delete(customer);
            logger.debug("Customer " + customer.getName() + " deleted");
            return true;
        }
        throw new RuntimeException("Customer can't be deleted cause he have created orders or income docs");
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
        logger.debug("Customer " + customer.getName() + " edited");
        return cretateDto(customer);
    }

    public Customer createCustomer(CustomerDto dto) {
        Customer createdCustomer = new Customer();
        createdCustomer.setName(dto.getName());
        createdCustomer.setOrg(organizationService.getByCode(dto.getOrgCode()));
        createdCustomer.setPhoneNum(dto.getPhone());
        return createdCustomer;
    }

    public CustomerDto cretateDto(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.setName(customer.getName());
        dto.setPhone(customer.getPhoneNum());
        dto.setOrgCode(customer.getOrg().getCode());
        return dto;
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public Page<CustomerDto> getPaginatedCustomer(String orgcode, Pageable page) {
        Organization org = organizationService.getByCode(orgcode);
        Page<Customer> goods = repository.findAllByOrgId(org.getId(), page);
        Page<CustomerDto> customerDto = goods.map(this::cretateDto);
        return customerDto;
    }

    public List<Customer> getByOrgCode(String code) {
        Organization org = organizationService.getByCode(code);
        return repository.findByOrgId(org.getId());

    }

    public Customer createDefoultCustomer(String orgCode) {
        Organization org = organizationService.getByCode(orgCode);
        Customer def = repository.findByPhoneNumAndOrgCode(org.getId().toString(), orgCode);
        if (def == null) {
            def = new Customer();
            def.setName(org.getName());
            def.setOrg(org);
            def.setPhoneNum(org.getId().toString());
            return repository.save(def);
        }
        return def;
    }

}
