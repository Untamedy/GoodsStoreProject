package com.store.goodsstore.services;

import com.store.goodsstore.dto.GoodsDto;
import com.store.goodsstore.entities.Goods;
import com.store.goodsstore.entities.GoodsCounter;
import com.store.goodsstore.entities.GoodsGroup;
import com.store.goodsstore.entities.GoodsPrice;
import com.store.goodsstore.repository.GoodsRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
    private GoodsPriceService priceService;
    @Autowired
    private GoodsGroupService groupService;

    public GoodsDto saveGoods(GoodsDto goodsDto) {
        GoodsDto newGoods = null;
        if (!repository.existsByCode(goodsDto.getCode())) {
            Goods goods = repository.save(createNewGoods(goodsDto));
            newGoods = createGoodsResponse(goods);
        }
        return newGoods;
    }

    public GoodsDto updateGoods(GoodsDto goodsDto) {
        Goods goods = repository.findByCode(goodsDto.getCode());
        if (null != goods) {
            goods.setCode(goodsDto.getCode());
            goods.setName(goodsDto.getName());
            goods.setUnit(goodsDto.getUnit());
            return createGoodsResponse(repository.save(goods));
        }
        throw new RuntimeException("Goods with code " + goodsDto.getCode() + " is not exist");
    }

    public boolean deleteGoods(GoodsDto goodsDto) {
        Goods goods = repository.findByCode(goodsDto.getCode());
        if (null != goods) {
            if (goodsCounterSecvice.getGoodsCount(goodsDto.getCode()) > 0) {
                return false;
            }
            goods.setVisible(false);
            return true;
        }
        return false;
    }

    public Goods createNewGoods(GoodsDto goodsDto) {
        Goods goods = new Goods();
        GoodsCounter counter = goodsCounterSecvice.createGoodsCounter(goodsDto);
        GoodsPrice price = priceService.createGoodsPrice(goodsDto);
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
    
    public Goods getByCode(GoodsDto dto){
       return repository.findByCode(dto.getCode());
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
        if (repository.existsByCode(code)) {
            return repository.findByCode(code);
        }
        throw new RuntimeException("Goods with " + code + " is not exist");

    }

    public boolean existByCode(String code) {

        return false;

    }

    public Page<GoodsDto> findByGroupId(int groupId, Pageable pageable) {  
        List<GoodsDto> dto = repository.findByGroupId(groupId).stream().map(tmp -> {
            return createGoodsResponse(tmp);
        }).collect(Collectors.toList());        
        Page<GoodsDto> page = new PageImpl<>(dto,pageable,dto.size());        
       return page;
    }

}
