package com.store.goodsstore.services;

import com.store.goodsstore.dto.GoodsDto;
import com.store.goodsstore.dto.IncomeDocDto;
import com.store.goodsstore.entities.Goods;
import com.store.goodsstore.entities.IncomingDoc;
import com.store.goodsstore.repository.IncomeRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public IncomeDocDto saveIncomeDoc(IncomeDocDto incomDto) {
        IncomingDoc doc = repository.save(createIncomeDoc(incomDto));
        return creteIncomeDocDto(doc);
    }
    
    public IncomeDocDto getByNum(int num){          
        return creteIncomeDocDto(repository.finbByNum(num));
    }

    public IncomingDoc createIncomeDoc(IncomeDocDto dto) {
        IncomingDoc incomingDoc = new IncomingDoc();
        incomingDoc.setIncomDate(dto.getIncomDate());
        incomingDoc.setNum(dto.getNum());
        incomingDoc.setCustomer(customerService.getCustomerByName(dto.getCustomer()));
        incomingDoc.setOrg(organizationService.getByName(dto.getOrgName()));
        incomingDoc.setGoods(convertToGoodsList(dto.getGoods()));
        incomingDoc.setIncomSum(countSum(dto.getGoods()));
        return incomingDoc;
    }

    public IncomeDocDto creteIncomeDocDto(IncomingDoc incomingDoc) {
        IncomeDocDto dto = new IncomeDocDto();
        dto.setIncomDate(incomingDoc.getIncomDate());
        dto.setNum(incomingDoc.getNum());
        dto.setCustomer(incomingDoc.getCustomer().getName());
        dto.setOrgName(incomingDoc.getOrg().getName());
        dto.setGoods(convertListToGoodsDto(incomingDoc.getGoods()));
        return dto;
    }

    private List<Goods> convertToGoodsList(List<GoodsDto> dtoList) {
        return dtoList.stream().map(tmp -> {
            Goods goods = goodsService.convertToGoods(tmp);
            return goods;
        }).collect(Collectors.toList());
    }

    private List<GoodsDto> convertListToGoodsDto(List<Goods> goods) {
        return goods.stream().map(tmp -> {
            GoodsDto goodsDto = goodsService.createGoodsResponse(tmp);
            return goodsDto;
        }).collect(Collectors.toList());

    }

    private double countSum(List<GoodsDto> goods) {
        double sum = 0;
        sum = goods.stream().map((g) -> g.getPrice()).reduce(sum, (accumulator, _item) -> accumulator + _item);
        return sum;
    }
}
