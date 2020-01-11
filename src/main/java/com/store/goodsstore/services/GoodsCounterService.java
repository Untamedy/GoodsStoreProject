package com.store.goodsstore.services;

import com.store.goodsstore.controllers.GoodsController;
import com.store.goodsstore.dto.GoodsDto;
import com.store.goodsstore.dto.IncomeDocResponseDto;
import com.store.goodsstore.entities.Goods;
import com.store.goodsstore.entities.GoodsCounter;
import com.store.goodsstore.entities.GoodsIncomePrice;
import com.store.goodsstore.repository.GoodsCounterRepository;
import com.store.goodsstore.repository.GoodsRepository;
import java.util.List;
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
public class GoodsCounterService {

    private static final Logger logger = LoggerFactory.getLogger(GoodsCounterService.class);

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
            logger.debug("Goods count " + goods.getName() + " incresed");

        }
    }

    public void decreaseGoodsQuantity(List<GoodsDto> goods) {       
        if (!goods.isEmpty()) {
            for (GoodsDto g : goods) {
                int count = goodsService.goodsCount(g.getCode());                            
                if (count == 0) {
                    throw new RuntimeException("Goods " + g.getName() + " with code =" + g.getCode() + " quantity is 0");
                }
                int newQuantity = count-1;
                Goods editGoods = goodsService.fingByCode(g.getCode());
                editGoods.getCounter().setQuantity(newQuantity);
                goodsRepository.save(editGoods);
                logger.debug("Goods count " + editGoods.getName() + " decreased");
            }
        }

    }

    public int getGoodsCount(String goodsCode) {
        GoodsCounter count = repository.findByGoodsCode(goodsCode);
        logger.debug("Counter for goods " + goodsCode + "found");
        return count.getQuantity();
    }

    public GoodsCounter createGoodsCounter(GoodsDto goodsDto) {
        logger.debug("New counter created");
        return new GoodsCounter(goodsDto.getQuantity());
    }

}
