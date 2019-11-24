package com.store.goodsstore.services;

import com.store.goodsstore.dto.GoodsCounterDto;
import com.store.goodsstore.dto.GoodsDto;
import com.store.goodsstore.dto.OrderDto;
import com.store.goodsstore.entities.GoodsCounter;
import com.store.goodsstore.repository.GoodsCounterRepository;
import com.store.goodsstore.repository.GoodsRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author YBolshakova
 */
@Service
public class GoodsCounterService {

    @Autowired
    GoodsCounterRepository repository;
    @Autowired
    GoodsService goodsService;
    @Autowired
    GoodsRepository goodsRepository;

    public List<GoodsDto> increaseGoodsQuantity(List<GoodsDto> goodsDto) {        
        for (GoodsDto g : goodsDto) {
            GoodsCounter counter = createGoodsCounter(g);
            int count = g.getQuantity();
            int newQuantity = counter.getQuantity() + count;
            counter.setQuantity(newQuantity);
            g.setQuantity(newQuantity);
            goodsService.saveGoods(g);
        }
        return goodsDto;
    }

    public List<GoodsDto> decreaseGoodsQuantity(OrderDto orderDto) {
        List<GoodsDto> dto = new ArrayList<>();
        for (GoodsDto g : orderDto.getGoods()) {
            GoodsCounter counter = createGoodsCounter(g);
            int count = g.getQuantity();
            if ((counter.getQuantity()-count)<0) {
                return dto;
            }
            int newQuantity = counter.getQuantity() - count;
            counter.setQuantity(newQuantity);
            g.setQuantity(newQuantity);
            goodsService.saveGoods(g);
        }
        return dto;
    }

    public GoodsCounter createGoodsCounter(GoodsDto goods) {
       GoodsCounter goodsCounter = repository.findByGoodsCode(goods.getCode());
       if(goodsCounter==null){
        return new GoodsCounter(goods.getQuantity());   
       }
       return goodsCounter;
    }

    public GoodsCounterDto createCounterDto(GoodsCounter counter) {
        GoodsCounterDto dto = new GoodsCounterDto();
        dto.setGoodsCode(counter.getGoods().getCode());
        dto.setQuantity(counter.getQuantity());
        return new GoodsCounterDto();

    }

    public int getGoodsCount(String goodsCode) {
        return repository.countQantityByGoodsCode(goodsCode);
    }

    GoodsCounter getCounterByGoodsCode(String code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
