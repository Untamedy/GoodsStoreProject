package com.store.goodsstore.services;

import com.store.goodsstore.dto.GoodsCounterDto;
import com.store.goodsstore.dto.GoodsDto;
import com.store.goodsstore.dto.IncomeDocResponseDto;
import com.store.goodsstore.dto.OrderDto;
import com.store.goodsstore.entities.Goods;
import com.store.goodsstore.entities.GoodsCounter;
import com.store.goodsstore.entities.GoodsIncomePrice;
import com.store.goodsstore.repository.GoodsCounterRepository;
import com.store.goodsstore.repository.GoodsRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void increaseGoodsQuantity(IncomeDocResponseDto incomeDocDto) {
        GoodsCounter counter = repository.findByGoodsCode(incomeDocDto.getGoodsCode());
        if (counter != null) {
            int newQuantity = incomeDocDto.getQuantity() + counter.getQuantity();
            counter.setQuantity(newQuantity);
            Goods goods = goodsService.fingByCode(incomeDocDto.getGoodsCode());
            GoodsIncomePrice incomePrice = goods.getIncomePrice();
            incomePrice.setIncomePrice(incomeDocDto.getIncomePrice());
            goods.setIncomePrice(incomePrice);
            goods.setCounter(counter);
            goodsRepository.save(goods);
        }
    }

    public void decreaseGoodsQuantity(List<GoodsDto> goods) {
        if (!goods.isEmpty()) {
            for (GoodsDto g : goods) {              
                int count = g.getQuantity();
                if (count == 0) {
                    throw new RuntimeException("Goods " + g.getName() + " with code =" + g.getCode() + " quantity is 0");
                }
                int newQuantity = count--; 
                Goods editGoods = goodsService.fingByCode(g.getCode());
                editGoods.getCounter().setQuantity(newQuantity);               
                goodsRepository.save(editGoods);
            }
        }

    }

    public int getGoodsCount(String goodsCode) {
        GoodsCounter count = repository.findByGoodsCode(goodsCode);
        return count.getQuantity();
    }

    GoodsCounter createGoodsCounter(GoodsDto goodsDto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
