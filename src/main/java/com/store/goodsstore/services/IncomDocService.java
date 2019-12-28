package com.store.goodsstore.services;

import com.store.goodsstore.dto.IncomeDocDto;
import com.store.goodsstore.dto.IncomeDocResponseDto;
import com.store.goodsstore.entities.Customer;
import com.store.goodsstore.entities.Goods;
import com.store.goodsstore.entities.IncomingDoc;
import com.store.goodsstore.entities.Organization;
import com.store.goodsstore.repository.IncomeRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author YBolshakova
 */
@Service
public class IncomDocService {

    @Autowired
    private IncomeRepository repository;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private GoodsCounterService goodsCounterService;

    @Transactional
    public void saveIncomeDoc(IncomeDocResponseDto incomDto) {
        IncomingDoc doc = createIncomeDoc(incomDto);
        goodsCounterService.increaseGoodsQuantity(incomDto);
        
        repository.save(doc);       
    }

    public IncomingDoc createIncomeDoc(IncomeDocResponseDto dto) {
        IncomingDoc incomingDoc = new IncomingDoc();
        incomingDoc.setDate(new Date());
        incomingDoc.setNum(givenNum());
        Goods goods = goodsService.fingByCode(dto.getGoodsCode());
        incomingDoc.setGoods(goods);
        Customer customer = customerService.getCustomerByPhoneAndOrgCode(dto.getCustomer(), dto.getOrgCode());
        incomingDoc.setCustomer(customer);
        Organization org = organizationService.getByCode(dto.getOrgCode());
        incomingDoc.setOrg(org);
        incomingDoc.setQuantity(dto.getQuantity());
        incomingDoc.setSum(countSum(goods.getIncomePrice().getIncomePrice(), dto.getQuantity()));
        return incomingDoc;
    }

    public IncomeDocDto creteIncomeDocDto(IncomingDoc incomingDoc) {
        IncomeDocDto dto = new IncomeDocDto();
        dto.setDate(incomingDoc.getDate());
        dto.setNum(incomingDoc.getNum());
        dto.setCustomerName(incomingDoc.getCustomer().getName());
        dto.setCustomerPhone(incomingDoc.getCustomer().getPhoneNum());
        dto.setOrgName(incomingDoc.getOrg().getName());
        dto.setGoodsName(incomingDoc.getGoods().getName());
        dto.setQuantity(incomingDoc.getQuantity());
        dto.setSum(incomingDoc.getSum());
        return dto;
    }

   
    @Transactional
    public IncomeDocDto getByNum(String num) {
        return creteIncomeDocDto(repository.findByNum(num));
    }

    @Transactional
    public List<IncomingDoc> getByCustomer(Customer customer) {
        return repository.findByCustomerId(customer.getId());

    }

    @Transactional
    public List<IncomeDocDto> getByPeriod(Date dateFrom, Date dateTo) {
        List<IncomeDocDto> dtoList = new ArrayList<>();
        List<IncomingDoc> list = repository.findAllByDateBetween(dateFrom, dateTo);
        if (!list.isEmpty()) {
            dtoList = list.stream().map((tmp) -> {
                return creteIncomeDocDto(tmp);
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

    public double countSum(double price, int quantity) {
        return (double) price * quantity;
    }
}
