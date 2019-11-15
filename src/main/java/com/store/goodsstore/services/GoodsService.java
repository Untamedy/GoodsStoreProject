package com.store.goodsstore.services;

import com.store.goodsstore.dto.GoodsDto;
import com.store.goodsstore.dto.GoodsPageRequest;
import com.store.goodsstore.dto.GoodsResponse;
import com.store.goodsstore.entities.Goods;
import com.store.goodsstore.repository.GoodsCounterRepository;
import com.store.goodsstore.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author YBolshakova
 */
@Service
public class GoodsService {

    @Autowired
    GoodsRepository repository;
    @Autowired
    GoodsCounterRepository counterRepository;

    public GoodsResponse saveGoods(GoodsDto goodsDto) {
        if (null == repository.findByCode(goodsDto.getCode())) {
            Goods goods = repository.save(convertToGoods(goodsDto));
            return createGoodsResponse(goods);
        }
        throw new RuntimeException("This goods is already exists");
    }

    public GoodsResponse updateGoodsName(GoodsDto goodsDto) {
        if (null != repository.findByCode(goodsDto.getCode())) {
            Goods goods = repository.updateGoodsName(goodsDto.getName(), goodsDto.getCode());
            return createGoodsResponse(goods);
        }

        throw new RuntimeException("Goods with code " + goodsDto.getCode() + " is not exist");

    }

    public boolean deleteGoods(GoodsDto goodsDto) {
        Goods goods = repository.findByCode(goodsDto.getCode());
        if (null != goods) {
            if (counterRepository.getGoodsSum(goodsDto.getCode()) > 0) {
                return false;
            }
            repository.delete(goods);
            goods.setVisible(false);
            return true;
        }
        return false;
    }

    public Goods convertToGoods(GoodsDto goodsDto) {
        Goods goods = new Goods();
        goods.setName(goodsDto.getName());
        goods.setCode(goodsDto.getCode());
        goods.setUnit(goodsDto.getUnit());
        goods.setVisible(true);
        return goods;
    }

    public GoodsResponse createGoodsResponse(Goods goods) {
        GoodsResponse response = new GoodsResponse();
        response.setId(goods.getId());
        response.setName(goods.getName());
        response.setCode(goods.getCode());
        response.setUnit(goods.getUnit());
        return response;
    }

  /*  public Page<GoodsResponse> findByStoreIdAndGoodsId(Integer id, GoodsPageRequest request) {
        Pageable page = PageRequest.of(request.getPage(), request.getSize());
        Page<GoodsResponse> goodsByStore = repository.findByStoreCode(request.getStoreCode(), page).map(((goods) -> {
            return createGoodsResponse(goods);
        }));
        return goodsByStore;
    }*/

    public int goodsCount(Integer storeId) {
        return repository.goodsCount(storeId);
    }
    
    public Goods fingByCode(String code){
        if(repository.existsByCode(code)){
            return repository.findByCode(code);
        }
        throw new RuntimeException("Goods with " + code + " is not exist");
        
    }
    
    public boolean existByCode(String code){
        
        return false;
        
    }

    public Page<GoodsResponse> findByStoreIdAndGroupId(Integer storeId, GoodsPageRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Page<GoodsResponse> findByGroupId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
