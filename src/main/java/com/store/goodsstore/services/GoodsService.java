package com.store.goodsstore.services;

import com.store.goodsstore.dto.EditGoodsDto;
import com.store.goodsstore.dto.GoodsDto;
import com.store.goodsstore.entities.Goods;
import com.store.goodsstore.entities.GoodsCounter;
import com.store.goodsstore.entities.GoodsGroup;
import com.store.goodsstore.entities.GoodsIncomePrice;
import com.store.goodsstore.entities.GoodsPrice;
import com.store.goodsstore.repository.GoodsRepository;
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
 * @author YBolshakova
 */
@Service
public class GoodsService {

    private static final Logger logger = LoggerFactory.getLogger(GoodsService.class);

    @Autowired
    private GoodsRepository repository;
    @Autowired
    private GoodsCounterService goodsCounterSecvice;
    @Autowired
    private GoodsGroupService groupService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private OrderService orderService;    
    @Autowired
    private IncomDocService incomeService;

    @Transactional
    public void saveGoods(GoodsDto goods) {
        if (!repository.existsByCode(goods.getCode())) {
            repository.save(createGoods(goods));
            logger.debug("Goods " + goods.getCode() + " saved");
        } else {
            throw new RuntimeException("Goods with code " + goods.getCode() + " is already exists");
        }

    }

    @Transactional
    public void updateGoods(EditGoodsDto goodsDto) {
        Goods goods = repository.findByCode(goodsDto.getCode());
        goods.setName(goodsDto.getName());
        goods.getPrice().setPrice(goodsDto.getPrice());
        logger.debug("Goods " + goods.getCode() + " edited");
        repository.save(goods);
    }

    @Transactional
    public void deleteGoods(String code) {
        Goods goods = repository.findByCode(code);
        if (null != goods) {
            if (goods.getCounter().getQuantity() == 0) {
                if(!orderService.ordersConteinGoods(goods.getId()).isEmpty()||!incomeService.incomeConteinGoodsId(goods.getId()).isEmpty()){
                    throw new RuntimeException("Goods use in sale or income documents");
                }               
            }else{
                 throw new RuntimeException("Goods count is more than 0");
            }
            logger.debug("Goods " + goods.getCode() + " deleted");
            repository.delete(goods);
        }
    }

    public Goods createGoods(GoodsDto goodsDto) {
        Goods goods = repository.findByCode(goodsDto.getCode());
        if (goods == null) {
            goods = new Goods();
            GoodsCounter counter = goodsCounterSecvice.createGoodsCounter(goodsDto);
            GoodsPrice price = createGoodsPrice(goodsDto);
            GoodsIncomePrice incomePrice = createIncomePrice(goodsDto);
            GoodsGroup group = groupService.getById(goodsDto.getGroupId());
            goods.setCounter(counter);
            goods.setPrice(price);
            goods.setGroup(group);
            goods.setIncomePrice(incomePrice);
            goods.setName(goodsDto.getName());
            goods.setCode(goodsDto.getCode());
            goods.setUnit(goodsDto.getUnit());
            goods.setVisible(true);
        }
        return goods;
    }

    public GoodsDto createGoodsResponse(Goods goods) {
        GoodsDto response = new GoodsDto();
        response.setName(goods.getName());
        response.setCode(goods.getCode());
        response.setUnit(goods.getUnit());
        response.setGroupId(goods.getGroup().getId());
        response.setIncomePrice(goods.getIncomePrice().getIncomePrice());
        response.setPrice(goods.getPrice().getPrice());
        response.setQuantity(goods.getCounter().getQuantity());
        return response;
    }

    @Transactional(readOnly = true)
    public int goodsCount(String goodsCode) {
        return goodsCounterSecvice.getGoodsCount(goodsCode);
    }

    @Transactional(readOnly = true)
    public Goods fingByCode(String code) {
        return repository.findByCode(code);
    }

    @Transactional(readOnly = true)
    public boolean existByCode(String code) {
        return repository.existsByCode(code);
    }

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
    public List<Goods> getByGroupId(int groupId) {
        return repository.findByGroupId(groupId);
    }

}
