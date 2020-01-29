package com.store.goodsstore.services;

import com.store.goodsstore.dto.GoodsDto;
import com.store.goodsstore.dto.IncomeDocDto;
import com.store.goodsstore.dto.IncomeDocResponseDto;
import com.store.goodsstore.entities.Customer;
import com.store.goodsstore.entities.Goods;
import com.store.goodsstore.entities.IncomingDoc;
import com.store.goodsstore.entities.Order;
import com.store.goodsstore.entities.Organization;
import com.store.goodsstore.repository.IncomeRepository;
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
 * @author YBolshakova
 */
@Service
public class IncomDocService {
    
      private static final Logger logger = LoggerFactory.getLogger(IncomDocService.class);

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
        logger.debug("IncomeDoc saved");
        repository.save(doc);       
    }
    
    public void saveFirstIncome(Goods goods,String orgCode){
        int quantity = goods.getCounter().getQuantity();
        IncomingDoc income = new IncomingDoc();
        income.setCustomer(customerService.createDefoultCustomer(orgCode));
        income.setDate(new Date());
        income.setGoods(goods);
        income.setOrg(organizationService.getByCode(orgCode));
        income.setQuantity(quantity);
        income.setSum(countSum(goods.getPrice().getPrice(), quantity));
        repository.save(income);
    }

    public IncomingDoc createIncomeDoc(IncomeDocResponseDto dto) {
        IncomingDoc incomingDoc = new IncomingDoc();
        incomingDoc.setDate(new Date());        
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
        dto.setNum(incomingDoc.getId());
        dto.setCustomerName(incomingDoc.getCustomer().getName());
        dto.setCustomerPhone(incomingDoc.getCustomer().getPhoneNum());
        dto.setOrgName(incomingDoc.getOrg().getName());
        dto.setGoodsName(incomingDoc.getGoods().getName());
        dto.setQuantity(incomingDoc.getQuantity());
        dto.setSum(incomingDoc.getSum());
        return dto;
    }

   
    

    @Transactional
    public List<IncomingDoc> getByCustomer(Customer customer) {
        return repository.findByCustomerId(customer.getId());

    }

    @Transactional
    public List<IncomeDocDto> getByPeriod(Date dateFrom, Date dateTo,Organization org) {
        List<IncomeDocDto> dtoList = new ArrayList<>();
        List<IncomingDoc> list = repository.findByDate(dateFrom, dateTo,org);
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

    public List<IncomingDoc> incomeConteinGoodsId(Integer id) {
      return repository.findByCustomerId(id);
    }
}
