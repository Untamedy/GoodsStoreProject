package com.store.goodsstore.services;

import com.store.goodsstore.dto.GoodsDto;
import com.store.goodsstore.entities.Goods;
import com.store.goodsstore.entities.GoodsCounter;
import com.store.goodsstore.entities.GoodsGroup;
import com.store.goodsstore.entities.GoodsIncomePrice;
import com.store.goodsstore.entities.GoodsPrice;
import com.store.goodsstore.repository.GoodsRepository;
import javax.persistence.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author YBolshakova
 */
@Service
public class GoodsService {

    @Autowired
    private GoodsRepository repository;
    @Autowired
    private GoodsCounterService goodsCounterSecvice;
    @Autowired
    private GoodsGroupService groupService;

    public GoodsDto saveGoods(GoodsDto goodsDto) {
        GoodsDto newGoods = null;
        if (!repository.existsByCode(goodsDto.getCode())) {
            Goods goods = new Goods();
            newGoods = createGoodsResponse(repository.save(createGoods(goodsDto, goods)));
        }
        return newGoods;
    }

    public GoodsDto updateGoods(GoodsDto goodsDto) {
        Goods goods = repository.findByCode(goodsDto.getCode());
        return createGoodsResponse(repository.save(createGoods(goodsDto, goods)));
    }

    public boolean deleteGoods(String code) {
        Goods goods = repository.findByCode(code);
        if (null != goods) {
            if (goodsCounterSecvice.getGoodsCount(code) > 0) {
                return false;
            }
            repository.delete(goods);
            return true;
        }
        return false;
    }

    public Goods createGoods(GoodsDto goodsDto, Goods goods) {
        //Goods goods = new Goods();
        GoodsCounter counter = goodsCounterSecvice.createGoodsCounter(goodsDto);
        GoodsPrice price = createGoodsPrice(goodsDto);
        GoodsIncomePrice incomePrice = createIncomePrice(goodsDto);
        GoodsGroup group = groupService.getGroupByname(goodsDto.getGroupName());
        goods.setName(goodsDto.getName());
        goods.setCode(goodsDto.getCode());
        goods.setUnit(goodsDto.getUnit());
        goods.setVisible(true);
        goods.setCounter(counter);
        goods.setPrice(price);
        goods.setGroup(group);
        return goods;
    }

    public GoodsDto createGoodsResponse(Goods goods) {
        GoodsDto response = new GoodsDto();
        response.setName(goods.getName());
        response.setCode(goods.getCode());
        response.setUnit(goods.getUnit());
        return response;
    }

    public int goodsCount(String goodsCode) {
        return goodsCounterSecvice.getGoodsCount(goodsCode);
    }

    public Goods fingByCode(String code) {
        return repository.findByCode(code);
    }

    public boolean existByCode(String code) {
        return repository.existsByCode(code);
    }

    public Page<GoodsDto> getPaginatedGoods(int groupId, Pageable page) {
        Page<Goods> goods = repository.findByGroupId(groupId, page);
        Page<GoodsDto> goodsDto = goods.map(this::createGoodsResponse);
        return goodsDto;
    }

    public GoodsPrice createGoodsPrice(GoodsDto goodsDto) {
        return new GoodsPrice(goodsDto.getPrice());
    }

    public GoodsIncomePrice createIncomePrice(GoodsDto goodsDto) {
        return new GoodsIncomePrice(goodsDto.getIncomePrice());
    }

}
