package com.store.goodsstore.services;

import com.store.goodsstore.dto.GoodsDto;
import com.store.goodsstore.dto.IncomeDocDto;
import com.store.goodsstore.entities.Customer;
import com.store.goodsstore.entities.Goods;
import com.store.goodsstore.entities.IncomingDoc;
import com.store.goodsstore.repository.IncomeRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
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

    
     @Transactional
    public IncomeDocDto saveIncomeDoc(IncomeDocDto incomDto) {
        IncomingDoc doc = repository.save(createIncomeDoc(incomDto));
        return creteIncomeDocDto(doc);
    }

    public IncomingDoc createIncomeDoc(IncomeDocDto dto) {
        IncomingDoc incomingDoc = new IncomingDoc();
        incomingDoc.setDate(new Date());
        incomingDoc.setNum(dto.getNum());
        incomingDoc.setCustomer(customerService.getCustomerByName(dto.getCustomer()));
        incomingDoc.setOrg(organizationService.getByName(dto.getOrgName()));
        incomingDoc.setGoods(convertToGoodsList(dto.getGoods()));
        incomingDoc.setSum(countSum(dto.getGoods()));
        return incomingDoc;
    }

    public IncomeDocDto creteIncomeDocDto(IncomingDoc incomingDoc) {
        IncomeDocDto dto = new IncomeDocDto();
        dto.setIncomeDate(incomingDoc.getDate());
        dto.setNum(incomingDoc.getNum());
        dto.setCustomer(incomingDoc.getCustomer().getName());
        dto.setOrgName(incomingDoc.getOrg().getName());
        dto.setGoods(convertListToGoodsDto(incomingDoc.getGoods()));
        dto.setIncomSum(incomingDoc.getSum());
        return dto;
    }

    private List<Goods> convertToGoodsList(List<GoodsDto> dtoList) {
        return dtoList.stream().map(tmp -> {
            Goods goods = goodsService.fingByCode(tmp.getCode());
            return goods;
        }).collect(Collectors.toList());
    }

    private List<GoodsDto> convertListToGoodsDto(List<Goods> goods) {
        return goods.stream().map(tmp -> {
            GoodsDto goodsDto = goodsService.createGoodsResponse(tmp);
            return goodsDto;
        }).collect(Collectors.toList());

    }
 @Transactional
    private double countSum(List<GoodsDto> goods) {
        double sum = 0;
        sum = goods.stream().map((g) -> g.getIncomePrice()).reduce(sum, (accumulator, _item) -> accumulator + _item);
        return sum;
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
}
